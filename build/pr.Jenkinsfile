node {
    def repoUrl = 'https://github.com/RiotGames/wwpub-sanity-studio.git'
    def repoDir = 'wwpub-sanity-studio'
    def credentialsId = 'ecadet-pcs'

    try {
        stage('Prepare Workspace') {
//             if (fileExists(repoDir)) {
//                 echo 'Repository already cloned. Pulling latest changes...'
//                 dir(repoDir) {
//                     // Retrieve credentials and configure Git to use them
//                     withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')]) {
//                         sh 'git config --global credential.helper "store --file=.git-credentials"'
//                         sh 'echo "https://https://github.com/RiotGames/wwpub-sanity-studio" > .git-credentials'
//                         sh 'git pull origin main'
//                     }
//                 }
//             } else {
                echo 'Cloning repository...'
                // Clone the repository using the credentials
                git url: repoUrl, branch: 'test', credentialsId: credentialsId
//             }
        }

        stage('Install Dependencies') {
            dir(repoDir) {
                def nodeModulesExists = fileExists('node_modules') && sh(returnStatus: true, script: 'test -d node_modules && ls -A node_modules') == 0

                if (!nodeModulesExists) {
                    echo 'Dependencies not found. Running npm install...'
//                     sh 'chsh -s /bin/zsh'
//                     sh 'curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash'
//                     sh 'nvm install 16'
//                     sh 'npm install'
//                      npm:install
                     withNPM(npmrcConfig: 'd0946c7b-85e2-444d-b198-03b07ceaa0c5') {
                         sh 'npm install'
                     }
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
}
