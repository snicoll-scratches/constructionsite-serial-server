package de.djgummikuh.constructionsite.demo;

import java.util.Map;

/**
 * @author Stephane Nicoll
 */
public class DefaultAssemblyRepository implements AssemblyRepository {

    private final Map<String, Assembly> assemblies;

    public DefaultAssemblyRepository(Map<String, Assembly> assemblies) {
        this.assemblies = assemblies;
    }

    @Override
    public Assembly findById(String id) {
        return this.assemblies.get(id);
    }

}
