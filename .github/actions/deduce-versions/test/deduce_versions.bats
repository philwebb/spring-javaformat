#!./test/libs/bats/bin/bats

load '../../.bats/libs/bats-support/load'
load '../../.bats/libs/bats-assert/load'

source "$PWD/deduce-versions.sh"

teardown() {
    rm .githuboutput
}

@test "deduce_versions() when 'milestone' should export versions" {
	repo=$( mock_git_repo "v1.2.3-M1" )
	cd "$repo"
    GITHUB_OUTPUT=".githuboutput"
    CURRENT_VERSION="1.2.3-SNAPSHOT"
    RELEASE_TYPE="milestone"
    run deduce_versions
    source .githuboutput
	assert [ "$status" -eq 0 ]
    assert [ "$STAGE_VERSION" = "1.2.3-M2" ]
    assert [ "$NEXT_VERSION" = "1.2.3-SNAPSHOT" ]
}

@test "deduce_versions() when 'release-candidate' should export versions" {
	repo=$( mock_git_repo "v1.2.3-M1" "v1.2.3-M2" "v1.2.3-RC1" )
	cd "$repo"
    GITHUB_OUTPUT=".githuboutput"
    CURRENT_VERSION="1.2.3-SNAPSHOT"
    RELEASE_TYPE="release-candidate"
    run deduce_versions
    source .githuboutput
	assert [ "$status" -eq 0 ]
    assert [ "$STAGE_VERSION" = "1.2.3-RC2" ]
    assert [ "$NEXT_VERSION" = "1.2.3-SNAPSHOT" ]
}

@test "deduce_versions() when 'release' should export versions" {
	repo=$( mock_git_repo "v1.2.3-M1" "v1.2.3-M2" "v1.2.3-RC1" )
	cd "$repo"
    GITHUB_OUTPUT=".githuboutput"
    CURRENT_VERSION="1.2.3-SNAPSHOT"
    RELEASE_TYPE="release"
    run deduce_versions
    source .githuboutput
	assert [ "$status" -eq 0 ]
    assert [ "$STAGE_VERSION" = "1.2.3" ]
    assert [ "$NEXT_VERSION" = "1.2.4-SNAPSHOT" ]
}

mock_git_repo() {
	local tmpdir=$(mktemp -d $BATS_TMPDIR/gitrepo.XXXXXX) >&2
	mkdir -p "$tmpdir" >&2
	cd "$tmpdir" >&2
	git init >&2
	echo "foo" > foo.txt
	git add foo.txt >&2
	git commit -m'Initial commit' >&2
	for tag in "$@"; do
		git tag "$tag" >&2
	done
	echo "$tmpdir"
}