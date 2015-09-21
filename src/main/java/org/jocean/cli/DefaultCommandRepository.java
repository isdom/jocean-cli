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
public class DefaultCommandRepository 
    implements CommandRepository {

    private final Map<String, CliCommand<? extends CliContext>> cmds = 
        new HashMap<String, CliCommand<? extends CliContext>>();

    public DefaultCommandRepository addCommand(
            final CliCommand<? extends CliContext> cmd) {
        cmds.put(cmd.getAction(), cmd);
        return  this;
    }
    
    public void setCommands(final Collection<? extends CliCommand<? extends CliContext>> cmds) {
        this.cmds.clear();
        
        for ( CliCommand<? extends CliContext> cmd : cmds ) {
            addCommand(cmd);
        }
    }
    
    public String[] getCommandActionAsArray() {
        return  cmds.keySet().toArray(new String[0]);
    }
    
    public CliCommand<?>[] getCommands() {
        return cmds.values().toArray(new CliCommand[0]);
    }

    @SuppressWarnings("unchecked")
    public <CTX extends CliContext> CliCommand<CTX> findCommandByAction(final String action) {
        return (CliCommand<CTX>)cmds.get(action);
    }

}
