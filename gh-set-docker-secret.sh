#! /bin/bash -e

gh secret set DOCKER_USER_ID --body "${DOCKER_USER_ID?}"
gh secret set DOCKER_TOKEN --body "${DOCKER_PASSWORD?}"