# Conventional Commits Guide

This document provides rules and guidelines for writing clear and effective commit messages in this project. We follow the [Conventional Commits specification](https://www.conventionalcommits.org/en/v1.0.0/), which provides a set of rules for creating an explicit commit history.

Adhering to this convention makes our commit history easier to read and navigate. It also allows us to automate CHANGELOG generation and semantic versioning.

## Commit Message Format

Each commit message consists of a **header**, an optional **body**, and an optional **footer**.

```
<type>[optional scope]: <description>

[optional body]

[optional footer(s)]
```

---

### Header

The header is the most important part of the commit message. It is mandatory and must follow the format: `<type>(<scope>): <description>`.

#### **`<type>`**
This describes the kind of change that you're committing. The following are the allowed types:

* **feat**: A new feature for the user.
* **fix**: A bug fix for the user.
* **docs**: Changes to documentation only.
* **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc).
* **refactor**: A code change that neither fixes a bug nor adds a feature.
* **perf**: A code change that improves performance.
* **test**: Adding missing tests or correcting existing tests.
* **build**: Changes that affect the build system or external dependencies (e.g., gulp, npm).
* **ci**: Changes to our CI configuration files and scripts (e.g., GitHub Actions).
* **chore**: Other changes that don't modify `src` or `test` files (e.g., updating dependencies).
* **revert**: Reverts a previous commit.

#### **`<scope>`** (Optional)
The scope provides additional contextual information. It's a noun describing the section of the codebase affected by the change.

* Examples: `api`, `auth`, `db`, `ui`, `parser`, `compiler`.
* If a change affects more than one scope, you can either omit the scope or use `*`.

#### **`<description>`**
The description contains a concise summary of the code changes.

* Use the imperative, present tense: "add" not "added" or "adds".
* Don't capitalize the first letter.
* Do not end the description with a period (`.`).
* Keep the description to 50 characters or less.

---

### Body (Optional)

The body is used to provide additional context and a more detailed explanation of the changes.

* Use a blank line between the header and the body.
* Explain the *what* and *why* vs. the *how*.
* Like the description, use the imperative, present tense.

---

### Footer (Optional)

The footer is used for two main purposes: referencing issues and indicating breaking changes.

* Use a blank line between the body (or header, if no body) and the footer.

#### **Breaking Changes**
A commit that introduces a breaking API change **MUST** start its footer with the text `BREAKING CHANGE:`, followed by a description of the change.

```
BREAKING CHANGE: The `getUser` endpoint now returns an object instead of an ID.
```

#### **Referencing Issues**
If the commit resolves, closes, or fixes an open issue, you should reference it in the footer using keywords.

* Use keywords like `Closes`, `Fixes`, `Resolves`.
* Example: `Closes #123` or `Fixes #42, Closes #112`.

---

## Examples

#### **Commit with description only**
```
feat: allow users to upload avatars
```

#### **Commit with a scope**
```
fix(api): correct user authentication middleware logic
```

#### **Commit with a body**
```
docs: update contribution guidelines

Add a new section explaining the Conventional Commits
standard we follow for all pull requests.
```

#### **Commit that closes an issue**
```
refactor(auth): simplify token generation logic

The previous implementation was overly complex and difficult to test.
This change streamlines the process without altering functionality.

Fixes #88
```

#### **Commit with a BREAKING CHANGE**
```
perf(db): switch to indexed views for user queries

This change significantly speeds up common user lookups by leveraging
indexed views instead of direct table scans.

BREAKING CHANGE: The `users` table schema has been modified.
Migrations must be run before deploying the new version.
```