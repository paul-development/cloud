package de.paul.dependencies;

import de.paul.CloudLauncher;
import de.paul.url.UrlDownloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * JavaDoc this file!
 * Created: 15.03.2023
 * <p>
 * author Paul.
 */
public class DependencyLoader {

    private final List<Dependency> dependencies;
    private final URLClassLoader classLoader = ((URLClassLoader) ClassLoader.getSystemClassLoader());

    public DependencyLoader() {
        this.dependencies = new ArrayList<>();
    }


    public void loadDependency(String groupId, String artifactId, String version) {
        CloudLauncher.getInstance().getCloudLogger().info("Loadin dependency " + artifactId + "...");
        dependencies.add(new Dependency(groupId, artifactId, version));
        if (new File("dependencies", artifactId + "-" + version + ".jar").exists()) {
            return;
        }
        new UrlDownloader("https://repo1.maven.org/maven2/" + groupId.replace(".", "/") + "/" + artifactId + "/" + version + "/" + artifactId + "-" + version + ".jar", new File("dependencies", artifactId + "-" + version + ".jar")).download();
    }

    public void addDependency(File file) {
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);

            method.invoke(classLoader, file.toURI().toURL());
        } catch (MalformedURLException | InvocationTargetException | NoSuchMethodException |
                 IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
