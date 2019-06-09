/**
 *
 */
package org.jocean.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.jocean.cli.cmd.StopException;
import org.jocean.idiom.ExceptionUtils;

/**
 * @author Marvin.Ma
 *
 */
public class CliShell<CTX extends CliContext> {

    private String              wordDelim = " ";


    public String execute(final CTX cmdCtx, final String cmdline) {
        final List<String> words = new ArrayList<String>();
        final StringTokenizer   st = new StringTokenizer(cmdline, wordDelim, false);
        while (st.hasMoreTokens()) {
            words.add( st.nextToken().trim() );
        }
        if ( words.isEmpty() ) {
            return "failed: cmdline is empty";
        }

        return execute(cmdCtx, words.toArray(new String[0]));
    }

    public String execute(final CTX cmdCtx, final String[] cmds) throws StopException {
        if ( null == cmds || cmds.length == 0 ) {
            return "failed: cmdline is empty";
        }

        final String action = cmds[0];

        final CliCommand<CTX> cmd = cmdCtx.getCommandRepository().findCommandByAction(action);

        if ( null == cmd ) {
            return "failed: can not find [" + action + "] command";
        }

        try {
            return cmd.execute(cmdCtx, Arrays.copyOfRange(cmds, 1, cmds.length));
        }
        catch( final Exception e) {
            if (e instanceof StopException ) {
                throw (StopException)e;
            }
            return ExceptionUtils.exception2detail(e);
        }
    }

    /**
     * @return the wordDelim
     */
    public String getWordDelim() {
        return wordDelim;
    }

    /**
     * @param wordDelim the wordDelim to set
     */
    public void setWordDelim(final String wordDelim) {
        this.wordDelim = wordDelim;
    }
}
