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
 * Legacy Dictionaries Cache Holder.
 *
 * @author CWDS TPT-2 Team
 */
public class LegacyDictionariesCache {

  /**
   * Cache holder Builder.
   *
   */
  public static class LegacyDictionariesCacheBuilder {

    private Map<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> daos
        = new ConcurrentHashMap<>();

    /**
     * Adds mapping Dictionary class dao, and returns self.
     *
     * @param clazz Class expected as result of Dao find call.
     * @param dao Dao for a Dictionary.
     * @param <T> Type of dictionary.
     * @return Self.
     */
    public <T extends SystemCodeTable> LegacyDictionariesCacheBuilder add(Class<T> clazz,
        BaseDaoImpl<T> dao) {
      daos.put(clazz, dao);
      return this;
    }

    /**
     * Build and return LegacyDictionariesCache.
     *
     * @return LegacyDictionariesCache.
     */
    public LegacyDictionariesCache build() {
      return new LegacyDictionariesCache(daos);
    }

    /**
     * Build, return LegacyDictionariesCache and preload cache.
     *
     * @return LegacyDictionariesCache.
     */
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

  /**
   * Private constructor.
   *
   * @param daos map of Dao to class.
   */
  private LegacyDictionariesCache(
      Map<Class<? extends SystemCodeTable>, BaseDaoImpl<? extends SystemCodeTable>> daos) {
    this(daos, false);
  }

  /**
   * Private constructor used by builder.
   *
   * @param daos map of Dao to class.
   * @param initialize flag.
   */
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

  /**
   * Finds dictionary entity by primaryKey.
   *
   * @param clazz Class of entity
   * @param key Primary Key.
   * @param <T> Generic entity type, entity must be extended from SystemCodeTable
   * @return Entity.
   */
  public <T extends SystemCodeTable> T findByPrimaryKey(Class<T> clazz, Serializable key) {
    try {
      return (T) cache.get(clazz).get(key);
    } catch (ExecutionException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }


  /**
   * Finds dictionary entity by defined predicate.
   *
   * @param clazz Class of entity.
   * @param filter Predicate
   * @param <T> Generic entity type, entity must be extended from SystemCodeTable
   * @return Entity.
   */
  public <T extends SystemCodeTable> T find(Class<T> clazz, Predicate<SystemCodeTable> filter) {
    try {
      return (T) cache.get(clazz).values().stream().filter(filter).findFirst().orElse(null);
    } catch (ExecutionException e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
