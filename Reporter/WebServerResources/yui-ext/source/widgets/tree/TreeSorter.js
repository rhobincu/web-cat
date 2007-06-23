/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

/**
 * @class Ext.tree.TreeSorter
 * Provides sorting of nodes in a TreePanel
 * 
 * @cfg {Boolean} folderSort True to sort leaf nodes under non leaf nodes
 * @cfg {String} property The named attribute on the node to sort by (defaults to text)
 * @cfg {String} dir The direction to sort (asc or desc) (defaults to asc)
 * @cfg {Boolean} caseSensitive true for case sensitive sort (defaults to false)
 * @cfg {Function} sortType A custom "casting" function used to convert node values before sorting
 * @constructor
 * @param {TreePanel} tree
 * @param {Object} config
 */
Ext.tree.TreeSorter = function(tree, config){
    Ext.apply(this, config);
    tree.on("beforechildrenrendered", this.doSort, this);
    tree.on("append", this.updateSort, this);
    tree.on("insert", this.updateSort, this);
    
    var dsc = this.dir && this.dir.toLowerCase() == "desc";
    var p = this.property || "text";
    var sortType = this.sortType;
    var fs = this.folderSort;
    var cs = this.caseSensitive === true;
    
    this.sortFn = function(n1, n2){
        if(fs){
            if(n1.leaf && !n2.leaf){
                return 1;
            }
            if(!n1.leaf && n2.leaf){
                return -1;
            }
        }
    	var v1 = sortType ? sortType(n1) : (cs ? n1[p] : n1[p].toUpperCase());
    	var v2 = sortType ? sortType(n2) : (cs ? n2[p] : n2[p].toUpperCase());
    	if(v1 < v2){
			return dsc ? +1 : -1;
		}else if(v1 > v2){
			return dsc ? -1 : +1;
        }else{
	    	return 0;
        }
    };
};

Ext.tree.TreeSorter.prototype = {
    doSort : function(node){
        node.sort(this.sortFn);
    },
    
    compareNodes : function(n1, n2){
        
        return (n1.text.toUpperCase() > n2.text.toUpperCase() ? 1 : -1);
    },
    
    updateSort : function(tree, node){
        if(node.childrenRendered){
            this.doSort.defer(1, this, [node]);
        }
    }
};