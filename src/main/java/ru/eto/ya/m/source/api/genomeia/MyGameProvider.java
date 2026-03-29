package ru.eto.ya.m.source.api.genomeia;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import net.fabricmc.loader.impl.FormattedException;
import net.fabricmc.loader.impl.game.GameProvider;
import net.fabricmc.loader.impl.game.GameProviderHelper;
import net.fabricmc.loader.impl.game.patch.GameTransformer;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.metadata.BuiltinModMetadata;
import net.fabricmc.loader.impl.metadata.ContactInformationImpl;
import net.fabricmc.loader.impl.util.Arguments;
import net.fabricmc.loader.impl.util.SystemProperties;
import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;


public class MyGameProvider implements GameProvider {
    public static final String CLIENT_ENTRYPOINT = "io.github.some_example_name.lwjgl3.Lwjgl3Launcher";
    public static final String[] ENTRYPOINTS = { CLIENT_ENTRYPOINT };

    public static final String PROPERTY_APP_DIRECTORY = "appDirectory";

    private static final GameTransformer TRANSFORMER = new MyGameTransformer();

    private Arguments arguments;
    private Path appJar;

    @Override
    public String getGameId() {
        return "genomeia";
    }

    @Override
    public String getGameName() {
        return "Genomeia";
    }

    @Override
    public String getRawGameVersion() {
        return "alpha-0.2.1";
    }

    @Override
    public String getNormalizedGameVersion() {
        return "0.2.1";
    }

    @Override
    public Collection<BuiltinMod> getBuiltinMods() {
        HashMap<String, String> contactMap = new HashMap<>();

        BuiltinModMetadata.Builder modMetadata = new BuiltinModMetadata.Builder(getGameId(), getNormalizedGameVersion())
                .setName(getGameName())
                .addAuthor("EtoYaM", contactMap)
                .setContact(new ContactInformationImpl(contactMap))
                .setDescription("Заглушка");

        BuiltinMod mod = new BuiltinMod(Collections.singletonList(appJar), modMetadata.build());

        return Collections.singletonList(mod);
    }

    @Override
    public String getEntrypoint() {
        return CLIENT_ENTRYPOINT;
    }

    @Override
    public Path getLaunchDirectory() {
        if (arguments == null) {
            return Paths.get(".");
        }

        return getLaunchDirectory(arguments);
    }

    private static Path getLaunchDirectory(Arguments arguments) {
        return Paths.get(arguments.getOrDefault(PROPERTY_APP_DIRECTORY, "."));
    }

    @Override
    public boolean requiresUrlClassLoader() {
        return false;
    }

    @Override
    public Set<BuiltinTransform> getBuiltinTransforms(String s) {
        return Set.of();
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean locateGame(FabricLauncher launcher, String[] args) {
        this.arguments = new Arguments();
        this.arguments.parse(args);

        List<String> appLocations = new ArrayList<>();
        if (System.getProperty(SystemProperties.GAME_JAR_PATH) != null) {
            appLocations.add(System.getProperty(SystemProperties.GAME_JAR_PATH));
        }
        appLocations.add("./Genomeia-" + getRawGameVersion() + ".jar");
        List<Path> existingAppLocations = appLocations.stream().map(p -> Paths.get(p).toAbsolutePath().normalize())
                .filter(Files::exists).toList();
        GameProviderHelper.FindResult result = GameProviderHelper.findFirst(existingAppLocations, new HashMap<>(), true, ENTRYPOINTS);

        if (result == null || result.path == null) {
            String appLocationsString = appLocations.stream().map(p -> (String.format("* %s", Paths.get(p).toAbsolutePath().normalize())))
                    .collect(Collectors.joining("\n"));

            Log.error(LogCategory.GAME_PROVIDER, "Не удалось найти игру! Мы искали в: \n" + appLocationsString);

            return false;
        }

        this.appJar = result.path;

        return true;
    }

    @Override
    public void initialize(FabricLauncher launcher) {
        TRANSFORMER.locateEntrypoints(launcher, Collections.singletonList(appJar));
    }

    @Override
    public GameTransformer getEntrypointTransformer() {
        return TRANSFORMER;
    }

    @Override
    public void unlockClassPath(FabricLauncher launcher) {
        launcher.addToClassPath(appJar);
    }

    @Override
    public void launch(ClassLoader loader) {
        try {
            Class<?> main = loader.loadClass(this.getEntrypoint());
            Method method = main.getMethod("main", String[].class);

            method.invoke(null, (Object) this.arguments.toArray());
        } catch (InvocationTargetException e) {
            throw new FormattedException("Приложение сломалось!", e.getCause());
        } catch (ReflectiveOperationException e) {
            throw new FormattedException("Не удалось запустить приложение", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Arguments getArguments() {
        return this.arguments;
    }

    @Override
    public String[] getLaunchArguments(boolean sanitize) {
        if (arguments == null) return new String[0];

        String[] ret = arguments.toArray();
        return ret;
    }
}