package pe.chalk.takoyaki.plugin;

import org.mozilla.javascript.RhinoException;
import pe.chalk.takoyaki.Takoyaki;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * @author ChalkPE <chalkpe@gmail.com>
 * @since 2015-11-05
 */
public class PluginLoader {
    public PluginBase load(Path path){
        return this.load(path.toFile());
    }

    public PluginBase load(File file){
        try{
            PluginBase plugin;

            if(file.getName().endsWith(".jar"))     plugin = this.loadJar(file);
            else if(file.getName().endsWith(".js")) plugin = new JavaScriptPlugin(file);
            else return null;

            Takoyaki.getInstance().getLogger().info("플러그인을 불러옵니다: " + plugin.getName() + (plugin.getVersion() != null ? " v" + plugin.getVersion() : ""));
            plugin.onLoad();

            return plugin;
        }catch(IOException | RhinoException | ReflectiveOperationException e){
            Takoyaki.getInstance().getLogger().error(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    //TODO: Load jar's classpath
    public PluginBase loadJar(File file) throws IOException, ReflectiveOperationException {
        JarFile jarFile = new JarFile(file);
        String mainClassName = jarFile.getManifest().getMainAttributes().getValue(Attributes.Name.MAIN_CLASS);

        ClassLoader loader = this.getClassLoaderFromJarFile(file, jarFile);
        Class<?> mainClass = Class.forName(mainClassName, true, loader);

        Class pluginClass = mainClass.asSubclass(PluginBase.class);
        return (PluginBase) pluginClass.newInstance();
    }

    public ClassLoader getClassLoaderFromJarFile(File file, JarFile jarFile) throws IOException {
        List<URL> urls = new ArrayList<>();
        urls.add(file.toURI().toURL());

        Manifest manifest = jarFile.getManifest();
        if(manifest != null){
            String classpath = manifest.getMainAttributes().getValue(Attributes.Name.CLASS_PATH);

            if(classpath != null){
                for(String path : classpath.split("\\s+")) urls.add(new File(file.getParentFile(), path).toURI().toURL());
            }
        }

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        if(urls.size() > 0){
            loader = new URLClassLoader(urls.toArray(new URL[urls.size()]), Thread.currentThread().getContextClassLoader());
        }
        return loader;
    }
}
