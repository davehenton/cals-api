def notifyBuild(String buildStatus, Exception e) {
    buildStatus = buildStatus ?: 'SUCCESSFUL'

    // Default values
    def colorName = 'RED'
    def colorCode = '#FF0000'
    def subject = "${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]'"
    def summary = """*${buildStatus}*: Job '${env.JOB_NAME} [${
        env.BUILD_NUMBER
    }]':\nMore detail in console output at <${env.BUILD_URL}|${env.BUILD_URL}>"""
    def details = """${buildStatus}: Job '${env.JOB_NAME} [${env.BUILD_NUMBER}]':\n
    Check console output at ${env.BUILD_URL} """
    // Override default values based on build status
    if (buildStatus == 'STARTED') {
        color = 'YELLOW'
        colorCode = '#FFFF00'
    } else if (buildStatus == 'SUCCESSFUL') {
        color = 'GREEN'
        colorCode = '#00FF00'
    } else {
        color = 'RED'
        colorCode = '#FF0000'
        details += "<p>Error message ${e.message}, stacktrace: ${e}</p>"
        summary += "\nError message ${e.message}, stacktrace: ${e}"
    }

    // Send notifications

    slackSend channel: "#cals-api", baseUrl: 'https://hooks.slack.com/services/', tokenCredentialId: 'slackmessagetpt2', color: colorCode, message: summary
    emailext(
            subject: subject,
            body: details,
            attachLog: true,
            recipientProviders: [[$class: 'DevelopersRecipientProvider']],
            to: "Leonid.Marushevskiy@osi.ca.gov, Alex.Kuznetsov@osi.ca.gov, Oleg.Korniichuk@osi.ca.gov, alexander.serbin@engagepoint.com, vladimir.petrusha@engagepoint.com"
    )
}


node('tpt2-slave') {
    def serverArti = Artifactory.server 'CWDS_DEV'
    def rtGradle = Artifactory.newGradleBuild()
    properties([buildDiscarder(logRotator(artifactDaysToKeepStr: '', artifactNumToKeepStr: '', daysToKeepStr: '', numToKeepStr: '5')), disableConcurrentBuilds(), [$class: 'RebuildSettings', autoRebuild: false, rebuildDisabled: false],
                parameters([
                        string(defaultValue: 'latest', description: '', name: 'APP_VERSION'),
                        string(defaultValue: 'development', description: '', name: 'branch'),
                        string(defaultValue: '', description: 'Used for mergerequest default is empty', name: 'refspec'),
                        booleanParam(defaultValue: false, description: 'Default release version template is: <majorVersion>_<buildNumber>-RC', name: 'RELEASE_PROJECT'),
                        string(defaultValue: "", description: 'Fill this field if need to specify custom version ', name: 'OVERRIDE_VERSION'),
                        booleanParam(defaultValue: true, description: '', name: 'USE_NEWRELIC'),
                        string(defaultValue: 'inventories/tpt2dev/hosts.yml', description: '', name: 'inventory'),
                        string(defaultValue: 'https://cals-api.dev.cwds.io/', description: '', name: 'APP_URL'),
                        string(defaultValue: 'https://web.dev.cwds.io/', description: 'Perry base URL', name: 'PERRY_URL'),
                        string(defaultValue: 'https://web.dev.cwds.io/perry/login', description: 'The URL where the login form posts a login information', name: 'LOGIN_FORM_TARGET_URL')
                ])
    ])
    try {
        stage('Preparation') {
            checkout([$class: 'GitSCM', branches: [[name: '$branch']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '433ac100-b3c2-4519-b4d6-207c029a103b', refspec: '$refspec', url: 'git@github.com:ca-cwds/cals-api.git']]])
            rtGradle.tool = "Gradle_35"
            rtGradle.resolver repo: 'repo', server: serverArti
            rtGradle.useWrapper = true
        }
        stage('Build') {
            echo("RELEASE: ${params.RELEASE_PROJECT}")
            echo("BUILD_NUMBER: ${BUILD_NUMBER}")
            echo("OVERRIDE_VERSION: ${params.OVERRIDE_VERSION}")
            def buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'jar -DRelease=$RELEASE_PROJECT -DBuildNumber=$BUILD_NUMBER -DCustomVersion=$OVERRIDE_VERSION'
        }
        stage('Unit Tests') {
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'test jacocoTestReport', switches: '--stacktrace'
        }
        stage('SonarQube analysis') {
            withSonarQubeEnv('Core-SonarQube') {
                buildInfo = rtGradle.run buildFile: 'build.gradle', switches: '--info', tasks: 'sonarqube'
            }
        }
        stage('License Report') {
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'downloadLicenses'
        }
        stage('Build Docker') {
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'createDockerImage'
        }
        stage('Clean Workspace') {
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'dropDockerImage'
            archiveArtifacts artifacts: '**/cals-api-*.jar,readme.txt', fingerprint: true
            cleanWs()
        }
        stage('Deploy Application') {
            checkout changelog: false, poll: false, scm: [$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '433ac100-b3c2-4519-b4d6-207c029a103b', url: 'git@github.com:ca-cwds/de-ansible.git']]]
            sh 'ansible-playbook -e NEW_RELIC_AGENT=$USE_NEWRELIC  -e CALS_API_VERSION=$APP_VERSION -i $inventory deploy-calsapi.yml --vault-password-file ~/.ssh/vault.txt -vv'
            cleanWs()
            sleep(20)
        }
        stage('Smoke Tests') {
            checkout([$class: 'GitSCM', branches: [[name: '$branch']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '433ac100-b3c2-4519-b4d6-207c029a103b', refspec: '$refspec', url: 'git@github.com:ca-cwds/cals-api.git']]])
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'smokeTest --stacktrace'
        }
        stage('Integration Tests') {
            checkout([$class: 'GitSCM', branches: [[name: '$branch']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: '433ac100-b3c2-4519-b4d6-207c029a103b', refspec: '$refspec', url: 'git@github.com:ca-cwds/cals-api.git']]])
            def gradlePropsText = """
         cals.api.url=${APP_URL}
	 perry.url=${PERRY_URL}
	 login.form.target.url=${LOGIN_FORM_TARGET_URL}
	 """
            writeFile file: "gradle.properties", text: gradlePropsText
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'integrationTest --stacktrace'
        }
        stage('Publish artifacts') {
            // Artifactory
            rtGradle.deployer.deployArtifacts = true
            buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'publish -DRelease=$RELEASE_PROJECT -DBuildNumber=$BUILD_NUMBER -DCustomVersion=$OVERRIDE_VERSION'
            rtGradle.deployer.deployArtifacts = false
            // Docker Hub
            withDockerRegistry([credentialsId: '6ba8d05c-ca13-4818-8329-15d41a089ec0']) {
                buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: 'publishDocker -DRelease=$RELEASE_PROJECT -DBuildNumber=$BUILD_NUMBER -DCustomVersion=$OVERRIDE_VERSION'
            }
            withDockerRegistry([credentialsId: '6ba8d05c-ca13-4818-8329-15d41a089ec0']) {
                buildInfo = rtGradle.run buildFile: 'build.gradle', tasks: ':docker-tests:dockerTestsPublish -DRelease=$RELEASE_PROJECT -DBuildNumber=$BUILD_NUMBER -DCustomVersion=$OVERRIDE_VERSION'
            }
        }
    } catch (Exception e) {
        errorcode = e
        currentBuild.result = "FAIL"
        notifyBuild(currentBuild.result, errorcode)
        throw e;
    } finally {
        archiveArtifacts allowEmptyArchive: true, artifacts: 'version.txt', fingerprint: true, onlyIfSuccessful: true
        fingerprint 'version.txt'
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/test', reportFiles: 'index.html', reportName: 'JUnit Report', reportTitles: 'JUnit tests summary'])
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/license', reportFiles: 'license-dependency.html', reportName: 'License Report', reportTitles: 'License summary'])
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/smokeTest', reportFiles: 'index.html', reportName: 'Smoke Tests Report', reportTitles: 'Smoke tests summary'])
        publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'build/reports/tests/integrationTest', reportFiles: 'index.html', reportName: 'Integration Tests Report', reportTitles: 'Integration tests summary'])
        cleanWs()
    }
}

