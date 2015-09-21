/**
 * 
 */
package org.jocean.cli;

/**
 * @author Marvin.Ma
 *
 */
public interface CliCommand<CTX extends CliContext> {
    
    public String getAction();
    
    public String getHelp();
    
    public String execute(final CTX ctx, final String... args) throws Exception;
}
