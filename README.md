# SourceAPI AOH2:DE || [Fabric](https://github.com/FabricMC/fabric-loader) GameProvider для [Age of History 2: Definitive Edition](https://store.steampowered.com/app/3381680/Age_of_History_2_Definitive_Edition/)
Проект предоставляет адаптацию загрузчика Fabric для игры AOH2:DE.
# Как использовать
## Загрузка модов
1. Скачать [последний релиз SourceAPI AOH2:DE](https://github.com/EtoYaM/SourceAPI_AOH2DE/releases).
2. Поместить его в папку с игрой.
3. Создать папку mods (там должны будут лежать моды).
4. Запустить SourceAPI.
### Важно:
- Убедитесь, что сама игра имеет название формата game.jar
- Убедитесь, что версия загрузчика соответствует версии игры. Это можно понять по названию файла загрузчика: он формируется в таком виде: SourceAPI_AOH2:DE-g*ВЕРСИЯ_ИГРЫ*_v*ВЕРСИЯ_ЗАГРУЗЧИКА*.jar (Например, `SourceAPI_AOH2:DE-g2.01_v1.1.0.jar` создан для версии игры `2.01`).
- Не скачивайте моды в непроверенных источниках, т.к. загрузчик не гарантирует их безопасность.

Можете поискать моды [в этом канале](https://vk.com/impossible_dreams_aoh).

Также вы можете скачать один из модов [здесь](https://github.com/EtoYaM/AOH2DE_GeneratedIspitaniya_mod/releases).

## Создание модов
Т.к. основа этого проекта - Fabric, то создание модов (по крайней мере в части миксинов) аналогично [созданию их на Minecraft](https://wiki.fabricmc.net/ru:tutorial:mixin_introduction).
### Шаг 1
- Создать пустой проект. Выбор редактора не имеет значения, но могу посоветовать [IntelliJ](https://www.jetbrains.com/idea/)
- Установить [последнюю версию SourceAPI](https://github.com/EtoYaM/SourceAPI_AOH2DE/releases), соответсвующую модифицируемой версии, и jar-файл самой игры в качестве библиотек. В компилируемый jar-файл их добавлять не нужно.
- Для удобства рекомендую добавить исходный код игры и пометить его в IDE.
### Шаг 2
- Создать необходимые классы (главный класс, реализующий ModInitializer, миксины по мере необходимости и прочие).

В ресурсах (или же в src) должны быть следующие файлы:
- `fabric.mod.json`
- `modid.mixins.json`. [Здесь](https://wiki.fabricmc.net/ru:tutorial:mixin_registration) небольшая документация по оформлению.

Примеры этих файлов можно увидеть [здесь](https://github.com/FabricMC/fabric-example-mod/tree/master/src/main/resources).

### Шаг 3
После всех проделанных действий можно скомпилировать мод в jar, переместить в папку mods (в каталоге с игрой) и запустить. Может, кто-то знает как это автоматизировать, то ваше дело, разрешаю.

- [Пример мода](https://github.com/EtoYaM/AOH2DE_GeneratedIspitaniya_mod)

### Будьте любезны, не создавайте вирусы
