repository=$(pwd)/distribution-repository

echo "Staging ${STAGE_VERSION} (next version will be ${NEXT_VERSION})"
./mvnw versions:set -DnewVersion=${STAGE_VERSION} -DgenerateBackupPoms=false
./mvnw org.eclipse.tycho:tycho-versions-plugin:update-eclipse-metadata
./mvnw --projects io.spring.javaformat:spring-javaformat-vscode-extension -P '!formatter-dependencies' antrun:run@update-version frontend:install-node-and-npm frontend:npm@update-package-lock

git config user.name "Spring Builds" > /dev/null
git config user.email "spring-builds@users.noreply.github.com" > /dev/null
git add pom.xml > /dev/null
git commit -m"Release v${STAGE_VERSION}" > /dev/null
git tag -a "v${STAGE_VERSION}" -m"Release v${STAGE_VERSION}" > /dev/null

./mvnw clean deploy -U -Dfull -DaltDeploymentRepository=distribution::default::file://${repository}

git reset --hard HEAD^ > /dev/null
if [[ ${NEXT_VERSION} != ${CURRENT_VERSION} ]]; then
	echo "Setting next development version (v${NEXT_VERSION})"
	./mvnw versions:set -DnewVersion=${NEXT_VERSION} -DgenerateBackupPoms=false
	./mvnw org.eclipse.tycho:tycho-versions-plugin:update-eclipse-metadata
	./mvnw --projects io.spring.javaformat:spring-javaformat-vscode-extension -P '!formatter-dependencies' antrun:run@update-version frontend:npm@update-package-lock
	sed -i "s/:release-version:.*/:release-version: ${STAGE_VERSION}/g" README.adoc
	sed -i "s/spring-javaformat-gradle-plugin:.*/spring-javaformat-gradle-plugin:${NEXT_VERSION}\"\)/g" samples/spring-javaformat-gradle-sample/build.gradle
	sed -i "s/spring-javaformat-checkstyle:.*/spring-javaformat-checkstyle:${NEXT_VERSION}\"\)/g" samples/spring-javaformat-gradle-sample/build.gradle
	sed -i "s|<spring-javaformat.version>.*</spring-javaformat.version>|<spring-javaformat.version>${NEXT_VERSION}</spring-javaformat.version>|" samples/spring-javaformat-maven-sample/pom.xml
	git add -u . > /dev/null
	git commit -m"Next development version (v${NEXT_VERSION})" > /dev/null
fi;

echo "distribution-repository=${repository}" >> "$GITHUB_OUTPUT"
