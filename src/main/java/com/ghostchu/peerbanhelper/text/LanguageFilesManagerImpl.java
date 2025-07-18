package com.ghostchu.peerbanhelper.text;

import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// No to-do anymore! This used for not only messages_fallback.yml! Keep the extent ability!
public final class LanguageFilesManagerImpl implements LanguageFilesManager {
    //distributionPath->[localeCode->OTA files]
    private final Map<String, YamlConfiguration> locale2ContentMapping = new ConcurrentHashMap<>();

    /**
     * Deploy new locale to TextMapper with cloud values and bundle values
     *
     * @param locale          The locale code
     * @param newDistribution The values from Distribution platform
     */
    @Override
    public void deploy(@NotNull String locale, @NotNull YamlConfiguration newDistribution) {
        if (!this.locale2ContentMapping.containsKey(locale)) {
            this.locale2ContentMapping.put(locale, newDistribution);
        } else {
            YamlConfiguration exists = this.locale2ContentMapping.get(locale);
            merge(exists, newDistribution);
        }
    }

    private void merge(@NotNull YamlConfiguration alreadyRegistered, @NotNull YamlConfiguration newConfiguration) {
        for (String key : newConfiguration.getKeys(true)) {
            if (newConfiguration.isConfigurationSection(key)) {
                continue;
            }
            alreadyRegistered.set(key, newConfiguration.get(key));
        }
    }

    public void fillMissing(@NotNull YamlConfiguration fallback) {
        for (YamlConfiguration value : this.locale2ContentMapping.values()) {
            mergeMissing(value, fallback);
        }
    }

    private void mergeMissing(@NotNull YamlConfiguration alreadyRegistered, @NotNull YamlConfiguration newConfiguration) {
        for (String key : newConfiguration.getKeys(true)) {
            if (newConfiguration.isConfigurationSection(key)) {
                continue;
            }
            if (alreadyRegistered.isSet(key)) {
                continue;
            }
            alreadyRegistered.set(key, newConfiguration.get(key));
        }
    }

    @Override
    public void destroy(@NotNull String locale) {
        this.locale2ContentMapping.remove(locale);
    }

    /**
     * Getting specific locale data under specific distribution data
     *
     * @param locale The specific locale
     * @return The locale data, null if never deployed
     */
    @Override
    public @Nullable YamlConfiguration getDistribution(@NotNull String locale) {
        return this.locale2ContentMapping.get(locale);
    }

    /**
     * Getting specific locale data under specific distribution data
     *
     * @return The locale data, null if never deployed
     */
    @Override
    public @NotNull Map<String, YamlConfiguration> getDistributions() {
        return locale2ContentMapping;
    }

    /**
     * Remove all locales data under specific distribution path
     *
     * @param distributionPath The distribution path
     */
    @Override
    public void remove(@NotNull String distributionPath) {
        this.locale2ContentMapping.remove(distributionPath);
    }

    /**
     * Remove specific locales data under specific distribution path
     *
     * @param distributionPath The distribution path
     * @param locale           The locale
     */
    @Override
    public void remove(@NotNull String distributionPath, @NotNull String locale) {
        if (this.locale2ContentMapping.containsKey(distributionPath)) {
            this.locale2ContentMapping.remove(locale);
        }
    }

    /**
     * Reset TextMapper
     */
    @Override
    public void reset() {
        this.locale2ContentMapping.clear();
    }


}
