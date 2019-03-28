/**
 *
 */
package org.jocean.cli;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.jocean.cli.mbean.CommandRepositoryMXBean;

/**
 * @author Marvin.Ma
 * @param <CTX>
 *
 */
public class DefaultCommandRepository implements CommandRepository, CommandRepositoryMXBean {

    @SuppressWarnings("rawtypes")
    private static final CliCommand[] EMPTY_CMDS = new CliCommand[0];
    private static final String[] EMPTY_STRS = new String[0];

    private final Map<String, CliCommand<? extends CliContext>> _cmds = new HashMap<>();

    @Override
    public Map<String, String> getCommandsDetail() {
        final Map<String, String> detail = new HashMap<>();
        for ( final Map.Entry<String, CliCommand<? extends CliContext>> entry : this._cmds.entrySet()) {
            detail.put(entry.getKey(), entry.getValue().getHelp());
        }

        return detail;
    }

    public DefaultCommandRepository addCommand(final CliCommand<? extends CliContext> cmd) {
        _cmds.put(cmd.getAction(), cmd);
        return  this;
    }

    public void addCommands(final Collection<? extends CliCommand<? extends CliContext>> cmds) {
//        this._cmds.clear();

        for ( final CliCommand<? extends CliContext> cmd : cmds ) {
            addCommand(cmd);
        }
    }

    public String[] getCommandActionAsArray() {
        return _cmds.keySet().toArray(EMPTY_STRS);
    }

    @Override
    public CliCommand<?>[] getCommands() {
        return _cmds.values().toArray(EMPTY_CMDS);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <CTX extends CliContext> CliCommand<CTX> findCommandByAction(final String action) {
        return (CliCommand<CTX>)_cmds.get(action);
    }
}
