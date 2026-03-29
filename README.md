# SourceAPI Genomeia || [Fabric](https://github.com/FabricMC/fabric-loader) GameProvider для [Genomeia](https://github.com/dendor0101/Genomeia)
Проект предоставляет адаптацию загрузчика Fabric для игры Genomeia.
# Как использовать
## Загрузка модов
1. Скачать [последний релиз SourceAPI Genomeia](https://github.com/EtoYaM/SourceAPIGenomeia/releases).
2. Поместить его в папку с игрой.
3. Создать папку mods (там должны будут лежать моды).
4. Запустить SourceAPI.
### Важно:
- Убедитесь, что игры имеет название формата Genomeia-*стадия*-*версия*.jar (Например, `Genomeia-alpha-0.2.1.jar`)
- Убедитесь, что версия загрузчика соответствует версии игры. Это можно понять по названию файла загрузчика: он формируется в таком виде: SourceAPIGenomeia-g*ВЕРСИЯ_ИГРЫ*_v*ВЕРСИЯ_ЗАГРУЗЧИКА*.jar (Например, `SourceAPIGenomeia-g0.2.1_v1.1.0.jar` создан для версии игры `0.2.1`).
- Не скачивайте моды в непроверенных источниках, т.к. загрузчик не гарантирует их безопасность.

Вы можете скачать тестовый мод [здесь](https://github.com/EtoYaM/TestModGenomeia/releases), либо поискать их в [сообществе Genomeia в telegram](https://t.me/GenomeiaCmty), либо зайти [сюда](https://github.com/EtoYaM/SourceAPIGenomeia/discussions/new?category=mods).

## Создание модов
Т.к. основа этого проекта - Fabric, то создание модов (по крайней мере в части миксинов) аналогично [созданию их на Minecraft](https://wiki.fabricmc.net/ru:tutorial:mixin_introduction).
### Шаг 1
- Создать пустой проект. Выбор редактора не имеет значения, но могу посоветовать [IntelliJ](https://www.jetbrains.com/idea/)
- Установить [последнюю версию SourceAPI](https://github.com/EtoYaM/SourceAPIGenomeia/releases), соответсвующую модифицируемой версии, и jar-файл самой игры в качестве библиотек. В компилируемый jar-файл их добавлять не нужно.
### Шаг 2
- Создать необходимые классы (главный класс, реализующий ModInitializer, миксины по мере необходимости и прочие).

В ресурсах (или же в src) должны быть следующие файлы:
- `fabric.mod.json`
- `modid.mixins.json`. [Здесь](https://wiki.fabricmc.net/ru:tutorial:mixin_registration) небольшая документация по оформлению.

Примеры этих файлов можно увидеть [здесь](https://github.com/FabricMC/fabric-example-mod/tree/master/src/main/resources).

### Шаг 3
После всех проделанных действий можно скомпилировать мод в jar, переместить в папку mods (в каталоге с игрой) и запустить. Может, кто-то знает как это автоматизировать, то ваше дело, разрешаю.

- [Пример мода](https://github.com/EtoYaM/TestModGenomeia)

### Будьте любезны, не создавайте вирусы
Можете добавить свой мод [сюда](https://github.com/EtoYaM/SourceAPIGenomeia/discussions/new?category=mods)