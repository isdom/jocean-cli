/**
 * 
 */
package org.jocean.cli.cmd;

import org.jocean.cli.CliCommand;
import org.jocean.cli.CliContext;

/**
 * @author Marvin.Ma
 *
 */
public class ExitCommand implements CliCommand<CliContext> {

    public String execute(final CliContext ctx, final String... args) throws Exception {
        throw new StopException();
    }

    public String getAction() {
        return "exit";
    }

    public String getHelp() {
        return null;
    }

}
