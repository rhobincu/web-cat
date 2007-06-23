/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 */

var TreeTest = function(){
    // shorthand
    var Tree = Ext.tree;
    
    return {
        init : function(){
            // yui-ext tree
            var tree = new Tree.TreePanel('tree', {
                animate:true, 
                loader: new Tree.TreeLoader({dataUrl:'get-nodes.php'}),
                enableDD:true,
                containerScroll: true,
                dropConfig: {appendOnly:true}
            });
            
            // add a tree sorter in folder mode
            new Tree.TreeSorter(tree, {folderSort:true});
            
            // set the root node
            var root = new Tree.AsyncTreeNode({
                text: 'yui-ext', 
                draggable:false, // disable root node dragging
                id:'source'
            });
            tree.setRootNode(root);
            
            // render the tree
            tree.render();
            
            root.expand(false, /*no anim*/ false);
            
            //-------------------------------------------------------------
            
            // YUI tree            
            var tree2 = new Tree.TreePanel('tree2', {
                animate:true, 
                //rootVisible: false,
                loader: new Ext.tree.TreeLoader({
                    dataUrl:'get-nodes.php',
                    baseParams: {lib:'yui'} // custom http params
                }),
                containerScroll: true,
                enableDD:true,
                dropConfig: {appendOnly:true}
            });
            
            // add a tree sorter in folder mode
            new Tree.TreeSorter(tree2, {folderSort:true});
            
            // add the root node
            var root2 = new Tree.AsyncTreeNode({
                text: 'Yahoo! UI Source', 
                draggable:false, 
                id:'yui'
            });
            tree2.setRootNode(root2);
            tree2.render();
            
            root2.expand(false, /*no anim*/ false);
        }
    };
}();

Ext.EventManager.onDocumentReady(TreeTest.init, TreeTest, true);