package gov.ca.cwds.cals.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.legacy.cms.entity.syscodes.SystemCodeTable;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author CWDS TPT-2 Team
 */
public class LegacyDictionariesCache {

  public static class LegacyDictionariesCacheBuilder {

    private Map<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> daos
        = new ConcurrentHashMap<>();

    public <T extends SystemCodeTable> LegacyDictionariesCacheBuilder add(Class<T> clazz,
        BaseDaoImpl<T> dao) {
      daos.put(clazz, dao);
      return this;
    }

    public LegacyDictionariesCache build() {
      return new LegacyDictionariesCache(daos);
    }

    public LegacyDictionariesCache buildAndLoad() {
      return new LegacyDictionariesCache(daos, true);
    }

  }

  private static Map<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> daoRegistry
      = new ConcurrentHashMap<>();

  private static LoadingCache<Class<? extends SystemCodeTable>, Map<Serializable, ? extends SystemCodeTable>> cache
      = CacheBuilder.newBuilder()
      .expireAfterAccess(1, TimeUnit.DAYS)
      .build(
          new CacheLoader<Class<? extends SystemCodeTable>, Map<Serializable, ? extends SystemCodeTable>>() {
            @Override
            public Map<Serializable, ? extends SystemCodeTable> load(
                Class<? extends SystemCodeTable> key) throws Exception {
              return Optional.ofNullable(daoRegistry.get(key))
                  .map(dao -> dao.findAll().stream()
                      .collect(Collectors.toMap(SystemCodeTable::getPrimaryKey, o -> o)))
                  .orElseThrow(() -> new IllegalArgumentException(
                      "There is no dao registered for the Class: " + key.getCanonicalName()));
            }
          });

  public LegacyDictionariesCache(
      Map<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> daos) {
    this(daos, false);
  }

  private LegacyDictionariesCache(
      Map<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> daos,
      boolean initialize) {
    daoRegistry = daos;
    if (initialize) {
      daos.keySet().forEach(aClass -> {
        try {
          cache.get(aClass);
        } catch (ExecutionException e) {
          throw new IllegalStateException(e.getMessage(), e);
        }
      });
    }
  }

  public <T extends SystemCodeTable> T findByPrimaryKey(Class<T> clazz, Serializable key) {
    try {
      return (T) cache.get(clazz).get(key);
    } catch (ExecutionException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }

  public <T extends SystemCodeTable> T find(Class<T> clazz, Predicate<SystemCodeTable> filter) {
    try {
      return (T) cache.get(clazz).values().stream().filter(filter).findFirst().orElse(null);
    } catch (ExecutionException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
