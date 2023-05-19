pipeline {
    agent any
#    tools {
#        jdk 'jdk13'
#        maven 'maven3'
#    }
    stages {
        stage('Clone sources') {
            steps {
                git 'https://github.com/liranfar/jenkinsFile-helloWorld.git'
            }
        }

        stage('Install') {
            steps {
                sh "mvn package"
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                    step([$class: 'JacocoPublisher',
                          execPattern: 'target/*.exec',
                          classPattern: 'target/classes',
                          sourcePattern: 'src/main/java',
                          exclusionPattern: 'src/test*'
                    ])
                }
            }
        }
        stage('Deploy to Nexus') {
            steps {
                configFileProvider([configFile(fileId: 'our_settings', variable: 'SETTINGS')]) {
                    sh "mvn -s $SETTINGS deploy  -DskipTests -Dnexus-releases-url=${env.NEXUS_RELEASES_URL} -Dnexus-snapshots-url=${env.NEXUS_SNAPSHOTS_URL}"
                }

            }
        }

        //TODO: docker build,push
        //TODO: deploy docker image
//        stage('Executing Ansible Playbooks') {
//            steps {
//                ansiblePlaybook(
//                        playbook: 'src/main/scripts/playbook.yml',
//                        inventory: 'src/main/scripts/inventory.ini')
//            }
//        }
    }
}
