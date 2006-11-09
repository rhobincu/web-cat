/*==========================================================================*\
 |  $Id: OldEditScriptPage.java,v 1.2 2006/11/09 17:55:51 stedwar2 Exp $
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU General Public License as published by
 |  the Free Software Foundation; either version 2 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU General Public License
 |  along with Web-CAT; if not, write to the Free Software
 |  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA
 |
 |  Project manager: Stephen Edwards <edwards@cs.vt.edu>
 |  Virginia Tech CS Dept, 660 McBryde Hall (0106), Blacksburg, VA 24061 USA
\*==========================================================================*/

package net.sf.webcat.grader;

import com.webobjects.foundation.*;
import com.webobjects.appserver.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.zip.ZipFile;
import net.sf.webcat.archives.FileUtilities;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * This class presents the list of scripts (grading steps) that
 * are available for selection.
 *
 * @author Stephen Edwards
 * @version $Id: OldEditScriptPage.java,v 1.2 2006/11/09 17:55:51 stedwar2 Exp $
 */
public class OldEditScriptPage
    extends GraderComponent
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * This is the default constructor
     * 
     * @param context The page's context
     */
    public OldEditScriptPage( WOContext context )
    {
        super( context );
    }


    //~ KVC Attributes (must be public) .......................................

    public WODisplayGroup fileDisplayGroup;
    public File           file; // For Repetition1
    public String  scriptDirectory;
    public boolean hasExistingFiles;
    public NSData  uploadedData;
    public String  uploadedName;
    public int     selectedIndex = -1;
    public int     index         = -1;
    public boolean createNew     = false;


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    public void appendToResponse( WOResponse response, WOContext context )
    {
        NSMutableArray files = new NSMutableArray();
        Step selectedStep = prefs().step();
        ScriptFile script = selectedStep.script();
        log.debug( "attempting to build file list for display" );
        hasExistingFiles = false;
        if ( script != null )
        {
            File scriptDirAsFile = new File( script.dirName() );
            scriptDirectory = scriptDirAsFile.getPath();
            if ( script.hasSubdir() )
            {
                log.debug( "existing script has a subdir" );
                collectAllFiles( scriptDirAsFile, files );
                hasExistingFiles = true;
            }
            else if ( script.mainFileName() != null )
            {
                log.debug( "adding " + script.mainFilePath() );
                files.addObject( new File( script.mainFilePath() ) );
                hasExistingFiles = true;
            }
            else if ( script.uploadedFileName() != null )
            {
                log.debug( "adding " + script.uploadedFileName() );
                files.addObject( new File( scriptDirectory,
                                           script.uploadedFileName() ) );
                hasExistingFiles = true;
            }
        }
        if ( hasExistingFiles )
        {
            fileDisplayGroup.setObjectArray( files );
            fileDisplayGroup.updateDisplayedObjects();
            selectedIndex = -1;
            if ( script != null && script.mainFileName() != null )
            {
                log.debug( "looking for " + script.mainFileName() );
                for ( int i = 0;
                      i < fileDisplayGroup.displayedObjects().count();
                      i++ )
                {
                    log.debug( "checking against "
                               + ( (File)fileDisplayGroup.displayedObjects()
                                   .objectAtIndex( i ) ).getPath() );
                    if ( ( (File)fileDisplayGroup.displayedObjects()
                           .objectAtIndex( i ) ).getPath()
                         .endsWith( script.mainFileName() ) )
                    {
                        selectedIndex = i;
                        break;
                    }
                }
            }
            if ( script != null && selectedIndex < 0 )
            {
                selectedIndex = 0;
                file = (File)fileDisplayGroup.displayedObjects()
                    .objectAtIndex( selectedIndex ); 
                script.setMainFileName( fileName() );
            }   
        }
        super.appendToResponse( response, context );
    }


    // ----------------------------------------------------------
    /* Checks for errors, then records the currently selected item.
     *
     * @returns true if the next page should be a detail edit view
     */
    protected boolean saveSelectionNeedsMoreEditing()
    {
        boolean needsEditing = false;
        if ( uploadedName != null )
        {
            uploadedName = ( new File( uploadedName ) ).getName();
        }
        log.debug( "uploaded name = " +
                   ( ( uploadedName == null ) ? "<null>" : uploadedName ) );
        ScriptFile script = prefs().step().script();
        log.debug( "uploaded data is "
                   + ( ( uploadedData == null) ? "<null>" : "not <null>" ) );
        if ( uploadedData != null )
        {
            log.debug( "uploaded data length = " + uploadedData.length() );
        }
        if ( uploadedData != null
             &&  uploadedName != null
             &&  !uploadedName.equals( "" ) )
        {
            needsEditing = true;
            if ( script == null )
            {
                log.debug( "creating a new script" );
                script = createNewScript();
            }
            else if ( script.uploadedFileName() == null )
            {
                log.debug( "recovering from failed script upload" );
            } 
            else
            {
                log.debug( "replacing an existing script" );
                // If there is an associated directory ...
                if ( script.hasSubdir() )
                {
                    File dir = new File( script.dirName() );
                    if ( dir.exists() )
                    {
                        log.debug( "deleting " + script.dirName() );
                        FileUtilities.deleteDirectory( script.dirName() );
                    }
                    else
                    {
                        log.debug( "script dir "
                                   + dir.getPath()
                                   + " does not exist!" );
                    }
                    script.setSubdirName( null );
                }
                else
                {
                    // Otherwise, just delete the main file
                    File mainFile = new File( script.mainFilePath() );
                    if ( mainFile.exists() )
                    {
                        log.debug( "deleting " + mainFile.getPath() );
                        mainFile.delete();
                    }
                    else
                    {
                        log.debug( "script file "
                                   + mainFile.getPath()
                                   + " does not exist!" );
                    }
                }
            }
            script.setUploadedFileName( uploadedName );
            script.setMainFileName( uploadedName );
            script.setLastModified( new NSTimestamp() );

            String subdirName = ScriptFile.convertToSubdirName( uploadedName );
            File check1 = new File( script.mainFilePath() );
            File check2 = new File( script.dirName(), subdirName );

            if ( check1.exists() || check2.exists() )
            {
                log.debug( "existing script with same name detected" );
                // cancel this upload
                script.setUploadedFileName( null );
                script.setMainFileName( null );
                needsEditing = true;
                errorMessage( "You have another script with this same "
                              + "name.  Please use a different file name." );
            }
            else
            {
                // Save the file to disk
                try
                {
                    log.debug( "saving to file " + script.mainFilePath() );
                    File scriptPath = new File( script.mainFilePath() );
                    scriptPath.getParentFile().mkdirs();
                    FileOutputStream out = new FileOutputStream( scriptPath );
                    uploadedData.writeToStream( out );
                    out.close();
                }
                catch ( java.io.IOException e )
                {
                    throw new NSForwardException( e );
                }

                if ( uploadedName.endsWith( ".zip" ) ||
                     uploadedName.endsWith( ".jar" ) )
                {
                    try
                    {
                        File zfile = new File( script.mainFilePath() );
                        //ZipFile zip = new ZipFile( script.mainFilePath() );
                        script.setSubdirName( subdirName );
                        log.debug( "unzipping to " + script.dirName() );
                        net.sf.webcat.archives.ArchiveManager.getInstance()
                            .unpack( new File( script.dirName() ), zfile );
                        //zip.close();
                        zfile.delete();
                    }
                    catch ( java.io.IOException e )
                    {
                        errorMessage( "There was an error unzipping "
                                      + "your file.  Please try again" );
                        script.setSubdirName( subdirName );
                        FileUtilities.deleteDirectory( script.dirName() );
                        log.warn( "error unzipping:", e );
                        // throw new NSForwardException( e );
                    }
                    script.setMainFileName( null );
                    needsEditing = true;
                }
            }
        }
        else if ( selectedIndex > -1 )
        {
            file = (File)fileDisplayGroup.displayedObjects()
                .objectAtIndex( selectedIndex ); 
            script.setMainFileName( fileName() );
            needsEditing = false;
        }
        else
        {
            needsEditing = true;
        }
        uploadedName = null;
        uploadedData = null;
        return needsEditing;
    }


    // ----------------------------------------------------------
    public WOComponent next()
    {
        WOComponent result = null;
        if ( !saveSelectionNeedsMoreEditing() )
        {
            result = super.next();
        }
        return result;
    }


    // ----------------------------------------------------------
    public boolean finishEnabled()
    {
        return hasExistingFiles;
    }


    // ----------------------------------------------------------
    public boolean applyEnabled()
    {
        return finishEnabled();
    }


    // ----------------------------------------------------------
    public boolean applyLocalChanges()
    {
        return !saveSelectionNeedsMoreEditing() && super.applyLocalChanges();
    }


    // ----------------------------------------------------------
    public ScriptFile createNewScript()
    {
        ScriptFile newScript = new ScriptFile();
        wcSession().localContext().insertObject( newScript );
        Step selectedStep = prefs().step();
        selectedStep.setScriptRelationship( newScript );
        newScript.setAuthorRelationship( wcSession().user() );
        return newScript;
    }


    // ----------------------------------------------------------
    public String fileName()
    {
        String thisFile = file.getPath();
        log.debug( "fileName() for " + thisFile );
        log.debug( "base = " + scriptDirectory );
        if ( scriptDirectory != null
             && thisFile.startsWith( scriptDirectory ) )
        {
            thisFile = thisFile.substring( scriptDirectory.length() + 1 );
        }
        log.debug( "result = " + thisFile );
        return thisFile;
    }


    // ----------------------------------------------------------
    public NSTimestamp fileDate()
    {
        return new NSTimestamp( file.lastModified() );
    }


    // ----------------------------------------------------------
    public String fileSize()
    {
        return Submission.fileSizeAsString( file.length() );
    }


    // ----------------------------------------------------------
    protected void collectAllFiles( File dir, NSMutableArray list )
    {
        if ( dir.isDirectory() )
        {
            File[] files = dir.listFiles();
            for ( int i = 0; i < files.length; i++ )
            {
                if ( files[i].isDirectory() )
                {
                    collectAllFiles( files[i], list );
                }
                else
                {
                    list.addObject( files[i] );
                }
            }
        }
        else
        {
            list.addObject( dir );
        }
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( OldEditScriptPage.class );
}
