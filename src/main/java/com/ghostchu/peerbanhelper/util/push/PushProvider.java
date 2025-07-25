package com.ghostchu.peerbanhelper.util.push;

import com.google.gson.JsonObject;
import org.bspfsystems.yamlconfiguration.configuration.ConfigurationSection;

public interface PushProvider {
    String getName();
    String getConfigType();
    JsonObject saveJson();
    ConfigurationSection saveYaml();
    boolean push(String title, String content);

}