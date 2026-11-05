# expense-tracker

[![Build](https://github.com/PIKAniki/expense-tracker-ci/actions/workflows/build.yml/badge.svg)](https://github.com/PIKAniki/expense-tracker-ci/actions/workflows/build.yml)

Консольное приложение для учёта личных расходов. Данные хранятся в файле `expenses.csv`.

## Сборка и запуск

    mvn -q package
    java -jar target/expense-tracker-*.jar add 2026-09-01 350.50 food
    java -jar target/expense-tracker-*.jar list

Команды: `add`, `list`, `total`, `month <yyyy-MM>` (сумма за месяц), `calc "<выражение>"` (калькулятор). Сумму можно вводить через точку или запятую.
Данные хранятся в файле expenses.csv в текущей папке.
Требуется Java 17 или новее.
Документация: [руководство пользователя](docs/user-guide.md), [разработка и CI](docs/development.md), [Javadoc](https://pikaniki.github.io/expense-tracker-ci/).
Автор: команда проекта.
Планы развития: docs/roadmap.md.
Глоссарий: docs/glossary.md.
Сборка и проверка проекта выполняются в GitHub Actions. Готовый jar публикуется на странице Releases.
