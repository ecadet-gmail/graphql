def repoUrl = 'https://github.com/your-repo/sanity-react-project.git'
    def repoDir = 'sanity-react-project'
    def credentialsId = 'ecadet-pcs'

    try {
        stage('Prepare Workspace') {
            if (fileExists(repoDir)) {
                echo 'Repository already cloned. Pulling latest changes...'
                dir(repoDir) {
                    // Retrieve credentials and configure Git to use them
                    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
                        sh 'git config --global credential.helper "store --file=.git-credentials"'
                        sh 'echo "https://${GIT_USERNAME}:${GIT_PASSWORD}@github.com" > .git-credentials'
                        sh 'git pull origin main'
                    }
                }
            } else {
                echo 'Cloning repository...'
                // Clone the repository using the credentials
                git url: repoUrl, branch: 'main', credentialsId: credentialsId
            }
        }

        stage('Install Dependencies') {
            dir(repoDir) {
                def nodeModulesExists = fileExists('node_modules') && sh(returnStatus: true, script: 'test -d node_modules && ls -A node_modules') == 0

                if (!nodeModulesExists) {
                    echo 'Dependencies not found. Running npm install...'
                    sh 'npm install'
                } else {
                    echo 'Dependencies already installed. Skipping npm install.'
                }
            }
        }

        echo 'Sanity CMS environment setup complete.'

    } catch (Exception e) {
        currentBuild.result = 'FAILURE'
        echo 'Sanity CMS environment setup failed.'
        throw e
    }