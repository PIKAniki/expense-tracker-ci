# Разработка

## Сборка и тесты

    mvn verify

Команда компилирует проект, запускает unit-тесты (`*Test`, плагин surefire), интеграционные тесты (`*IT`, плагин failsafe) и строит отчёт о покрытии JaCoCo в `target/site/jacoco`. Документация API: `mvn javadoc:javadoc`, результат в `target/reports/apidocs`.

## Ветки

- `main` – стабильная ветка. Прямой push запрещён правилом репозитория: изменения попадают только через pull request и только если проверка `build` прошла.
- `development` – разработка новых функций и исправлений.
- `release/<версия>` – подготовка выпуска: версия в `pom.xml`, `CHANGELOG.md`, мелкие исправления.
- короткие ветки под отдельную задачу (например, `docs`) – вливаются в `main` или `development` через pull request.

## CI/CD

GitHub Actions, три workflow в `.github/workflows`:

| Файл | Когда запускается | Что делает |
|------|-------------------|------------|
| `build.yml` | push в `main`, `development`, `release/**`; pull request в `main` и `development`; вручную | `mvn -B verify`, отчёты тестов и покрытия сохраняются как артефакт `test-reports` |
| `docs.yml` | после успешного `Build` по push в `main` | генерирует Javadoc и публикует его на [GitHub Pages](https://pikaniki.github.io/expense-tracker-ci/) |
| `release.yml` | push тега `v*` | прогоняет тесты, собирает jar и создаёт GitHub Release |

Push в короткие ветки сборку не запускает: такие ветки проверяются через pull request, иначе каждый их коммит проверялся бы дважды.

## Выпуск версии

1. Создать ветку `release/X.Y.Z` от `main`, выставить версию `X.Y.Z` в `pom.xml`, дополнить `CHANGELOG.md`.
2. Открыть pull request в `main` и включить auto-merge.
3. После слияния поставить на `main` тег `vX.Y.Z` и отправить его – workflow `release.yml` создаст релиз с jar.
4. Поднять версию в `main` до следующей `-SNAPSHOT`.
