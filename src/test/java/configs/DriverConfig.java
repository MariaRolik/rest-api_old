package configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:config/${env}.properties"
})
public interface DriverConfig extends Config {
    @DefaultValue("chrome")
    String browser();

    @DefaultValue("100.0")
    String browserVersion();

    @DefaultValue("1920x1080")
    String browserSize();

    @DefaultValue("eager")
    String pageLoadStrategy();

    @DefaultValue("false")
    Boolean isRemote();

    String browserRemoteUrl();
}