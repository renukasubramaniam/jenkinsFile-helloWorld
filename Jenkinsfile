pipeline {
    agent any
    tools {
        jdk 'jdk8'
//        maven 'maven3'
    }
    stages {
        stage('Install') {
            steps {
                sh "mvn clean test"
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                }
            }
        }
//        stage('Deploy to Nexus') {
//            steps {
//                configFileProvider([configFile(fileId: 'our_settings', variable: 'SETTINGS')]) {
//                    sh "mvn -s $SETTINGS deploy  -DskipTests -Dnexus-releases-url=${env.NEXUS_RELEASES_URL} -Dnexus-snapshots-url=${env.NEXUS_SNAPSHOTS_URL}"
//                }
//
//            }
//        }
        stage('Executing Ansible Playbooks') {
            steps {
                ansiblePlaybook(
                        playbook: 'src/main/scripts/playbook.yml',
                        inventory: 'src/main/scripts/inventory.ini')
            }
        }
    }
}