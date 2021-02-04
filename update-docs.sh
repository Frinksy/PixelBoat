#!/bin/bash

cd Implementation &&
chmod +x gradlew &&
echo Generating new javadocs &&
./gradlew javadoc &&
echo Removing all javadocs from website &&
rm -rf ../docs/* &&
echo Copying javadocs to website &&
cp -r core/build/docs/javadoc/* ../docs/  &&
cd .. &&
echo Generating UML images &&
cd UML &&
chmod +x generate_images.sh &&
./generate_images.sh &&
cd .. &&
echo Set up Git &&
git config --local user.email "13720823+Frinksy@users.noreply.github.com" &&
git config --local user.name "Frinksy Workflow" &&
git fetch && git checkout ${GITHUB_HEAD_REF} &&
echo Stage all changes &&
git add docs/ &&
git add UML/PNG/ && git add UML/SVG/ &&
echo Check for changes

if git diff --staged --quiet ; then
    echo No changes found
else
    echo Changes found, creating a commit &&
    git commit -m "Update docs via Github Workflow" &&
    echo Push changes to ${GITHUB_HEAD_REF##* } &&
    git remote -v &&
    git push 
fi
echo Done!