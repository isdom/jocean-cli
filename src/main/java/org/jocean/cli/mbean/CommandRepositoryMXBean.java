package org.jocean.cli.mbean;

import java.util.Map;

public interface CommandRepositoryMXBean {
    public Map<String, String> getCommandsDetail();
}
