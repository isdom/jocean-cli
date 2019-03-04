/**
 *
 */
package org.jocean.cli;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Marvin.Ma
 * @param <CTX>
 *
 */
public class DefaultCommandRepository implements CommandRepository {

    @SuppressWarnings("rawtypes")
    private static final CliCommand[] EMPTY_CMDS = new CliCommand[0];
    private static final String[] EMPTY_STRS = new String[0];

    private final Map<String, CliCommand<? extends CliContext>> cmds = new HashMap<>();

    public DefaultCommandRepository addCommand(final CliCommand<? extends CliContext> cmd) {
        cmds.put(cmd.getAction(), cmd);
        return  this;
    }

    public void setCommands(final Collection<? extends CliCommand<? extends CliContext>> cmds) {
        this.cmds.clear();

        for ( final CliCommand<? extends CliContext> cmd : cmds ) {
            addCommand(cmd);
        }
    }

    public String[] getCommandActionAsArray() {
        return cmds.keySet().toArray(EMPTY_STRS);
    }

    @Override
    public CliCommand<?>[] getCommands() {
        return cmds.values().toArray(EMPTY_CMDS);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <CTX extends CliContext> CliCommand<CTX> findCommandByAction(final String action) {
        return (CliCommand<CTX>)cmds.get(action);
    }
}
