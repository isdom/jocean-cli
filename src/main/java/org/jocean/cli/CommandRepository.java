/**
 * 
 */
package org.jocean.cli;

/**
 * @author Marvin.Ma
 * @param <CTX>
 *
 */
public interface CommandRepository {
	
	public <CTX extends CliContext> CliCommand<CTX> findCommandByAction(final String action);
	
	public CliCommand<?>[]	getCommands();
	
}
