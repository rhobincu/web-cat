/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

Ext.LayoutRegion=function(_1,_2,_3){Ext.LayoutRegion.superclass.constructor.call(this,_1,_2,_3,true);var dh=Ext.DomHelper;this.el=dh.append(_1.el.dom,{tag:"div",cls:"x-layout-panel x-layout-panel-"+this.position},true);this.titleEl=dh.append(this.el.dom,{tag:"div",unselectable:"on",cls:"x-unselectable x-layout-panel-hd x-layout-title-"+this.position,children:[{tag:"span",cls:"x-unselectable x-layout-panel-hd-text",unselectable:"on",html:"&#160;"},{tag:"div",cls:"x-unselectable x-layout-panel-hd-tools",unselectable:"on"}]},true);this.titleEl.enableDisplayMode();this.titleTextEl=this.titleEl.dom.firstChild;this.tools=Ext.get(this.titleEl.dom.childNodes[1],true);this.closeBtn=this.createTool(this.tools.dom,"x-layout-close");this.closeBtn.enableDisplayMode();this.closeBtn.on("click",this.closeClicked,this);this.closeBtn.hide();this.bodyEl=dh.append(this.el.dom,{tag:"div",cls:"x-layout-panel-body"},true);this.visible=false;this.collapsed=false;this.hide();this.on("paneladded",this.validateVisibility,this);this.on("panelremoved",this.validateVisibility,this);this.applyConfig(_2);};Ext.extend(Ext.LayoutRegion,Ext.BasicLayoutRegion,{applyConfig:function(c){if(c.collapsible&&this.position!="center"&&!this.collapsedEl){var dh=Ext.DomHelper;this.collapseBtn=this.createTool(this.tools.dom,"x-layout-collapse-"+this.position);this.collapseBtn.on("click",this.collapse,this);this.collapseBtn.enableDisplayMode();this.collapsedEl=dh.append(this.mgr.el.dom,{cls:"x-layout-collapsed x-layout-collapsed-"+this.position,children:[{cls:"x-layout-collapsed-tools",children:[{cls:"x-layout-ctools-inner"}]}]},true);if(c.floatable!==false){this.collapsedEl.addClassOnOver("x-layout-collapsed-over");this.collapsedEl.on("click",this.collapseClick,this);}if(c.showPin===true||this.showPin==true){this.stickBtn=this.createTool(this.tools.dom,"x-layout-stick");this.stickBtn.enableDisplayMode();this.stickBtn.on("click",this.expand,this);this.stickBtn.hide();}if(c.collapsedTitle&&(this.position=="north"||this.position=="south")){this.collapsedTitleTextEl=dh.append(this.collapsedEl.dom,{tag:"div",cls:"x-unselectable x-layout-panel-hd-text",id:"message",unselectable:"on",style:{"float":"left"}});this.collapsedTitleTextEl.innerHTML=c.collapsedTitle;}this.expandBtn=this.createTool(this.collapsedEl.dom.firstChild.firstChild,"x-layout-expand-"+this.position);this.expandBtn.on("click",this.expand,this);}if(this.collapseBtn){this.collapseBtn.setVisible(c.collapsible==true);}this.cmargins=c.cmargins||this.cmargins||(this.position=="west"||this.position=="east"?{top:0,left:2,right:2,bottom:0}:{top:2,left:0,right:0,bottom:2});this.margins=c.margins||this.margins||{top:0,left:0,right:0,bottom:0};this.bottomTabs=c.tabPosition!="top";this.autoScroll=c.autoScroll||false;if(this.autoScroll){this.bodyEl.setStyle("overflow","auto");}else{this.bodyEl.setStyle("overflow","hidden");}if((!c.titlebar&&!c.title)||c.titlebar===false){this.titleEl.hide();}else{this.titleEl.show();if(c.title){this.titleTextEl.innerHTML=c.title;}}this.duration=c.duration||0.3;this.slideDuration=c.slideDuration||0.45;this.config=c;if(c.collapsed){this.collapse(true);}},isVisible:function(){return this.visible;},setCollapsedTitle:function(_7){_7=_7||"&#160;";if(this.collapsedTitleTextEl){this.collapsedTitleTextEl.innerHTML=_7;}},getBox:function(){var b;if(!this.collapsed){b=this.el.getBox(false,true);}else{b=this.collapsedEl.getBox(false,true);}return b;},getMargins:function(){return this.collapsed?this.cmargins:this.margins;},highlight:function(){this.el.addClass("x-layout-panel-dragover");},unhighlight:function(){this.el.removeClass("x-layout-panel-dragover");},updateBox:function(_9){this.box=_9;if(!this.collapsed){this.el.dom.style.left=_9.x+"px";this.el.dom.style.top=_9.y+"px";this.updateBody(_9.width,_9.height);}else{this.collapsedEl.dom.style.left=_9.x+"px";this.collapsedEl.dom.style.top=_9.y+"px";this.collapsedEl.setSize(_9.width,_9.height);}if(this.tabs){this.tabs.autoSizeTabs();}},updateBody:function(w,h){if(w!==null){this.el.setWidth(w);w-=this.el.getBorderWidth("rl");}if(h!==null){this.el.setHeight(h);h=this.titleEl.isDisplayed()?h-(this.titleEl.getHeight()||0):h;h-=this.el.getBorderWidth("tb");this.bodyEl.setHeight(h);if(this.tabs){h=this.tabs.syncHeight(h);}}if(this.panelSize){w=w!==null?w:this.panelSize.width;h=h!==null?h:this.panelSize.height;}if(this.activePanel){var el=this.activePanel.getEl();w=w!==null?w:el.getWidth();h=h!==null?h:el.getHeight();this.panelSize={width:w,height:h};this.activePanel.setSize(w,h);}if(Ext.isIE&&this.tabs){this.tabs.el.repaint();}},getEl:function(){return this.el;},hide:function(){if(!this.collapsed){this.el.dom.style.left="-2000px";this.el.hide();}else{this.collapsedEl.dom.style.left="-2000px";this.collapsedEl.hide();}this.visible=false;this.fireEvent("visibilitychange",this,false);},show:function(){if(!this.collapsed){this.el.show();}else{this.collapsedEl.show();}this.visible=true;this.fireEvent("visibilitychange",this,true);},closeClicked:function(){if(this.activePanel){this.remove(this.activePanel);}},collapseClick:function(e){if(this.isSlid){e.stopPropagation();this.slideIn();}else{e.stopPropagation();this.slideOut();}},collapse:function(_e){if(this.collapsed){return;}this.collapsed=true;if(this.split){this.split.el.hide();}if(this.config.animate&&_e!==true){this.fireEvent("invalidated",this);this.animateCollapse();}else{this.el.setLocation(-20000,-20000);this.el.hide();this.collapsedEl.show();this.fireEvent("collapsed",this);this.fireEvent("invalidated",this);}},animateCollapse:function(){},expand:function(e,_10){if(e){e.stopPropagation();}if(!this.collapsed){return;}if(this.isSlid){this.afterSlideIn();_10=true;}this.collapsed=false;if(this.config.animate&&_10!==true){this.animateExpand();}else{this.el.show();if(this.split){this.split.el.show();}this.collapsedEl.setLocation(-2000,-2000);this.collapsedEl.hide();this.fireEvent("invalidated",this);this.fireEvent("expanded",this);}},animateExpand:function(){},initTabs:function(){this.bodyEl.setStyle("overflow","hidden");var ts=new Ext.TabPanel(this.bodyEl.dom,this.bottomTabs);if(this.config.hideTabs){ts.stripWrap.setDisplayed(false);}this.tabs=ts;ts.resizeTabs=this.config.resizeTabs===true;ts.minTabWidth=this.config.minTabWidth||40;ts.maxTabWidth=this.config.maxTabWidth||250;ts.preferredTabWidth=this.config.preferredTabWidth||150;ts.monitorResize=false;ts.bodyEl.setStyle("overflow",this.config.autoScroll?"auto":"hidden");ts.bodyEl.addClass("x-layout-tabs-body");this.panels.each(this.initPanelAsTab,this);},initPanelAsTab:function(_12){var ti=this.tabs.addTab(_12.getEl().id,_12.getTitle(),null,this.config.closeOnTab&&_12.isClosable());if(_12.tabTip){ti.setTooltip(_12.tabTip);}ti.on("activate",function(){this.setActivePanel(_12);},this);if(this.config.closeOnTab){ti.on("beforeclose",function(t,e){e.cancel=true;this.remove(_12);},this);}return ti;},updatePanelTitle:function(_16,_17){if(this.activePanel==_16){this.updateTitle(_17);}if(this.tabs){var ti=this.tabs.getTab(_16.getEl().id);ti.setText(_17);if(_16.tabTip){ti.setTooltip(_16.tabTip);}}},updateTitle:function(_19){if(this.titleTextEl&&!this.config.title){this.titleTextEl.innerHTML=(typeof _19!="undefined"&&_19.length>0?_19:"&#160;");}},setActivePanel:function(_1a){_1a=this.getPanel(_1a);if(this.activePanel&&this.activePanel!=_1a){this.activePanel.setActiveState(false);}this.activePanel=_1a;_1a.setActiveState(true);if(this.panelSize){_1a.setSize(this.panelSize.width,this.panelSize.height);}this.closeBtn.setVisible(!this.config.closeOnTab&&!this.isSlid&&_1a.isClosable());this.updateTitle(_1a.getTitle());if(this.tabs){this.fireEvent("invalidated",this);}this.fireEvent("panelactivated",this,_1a);},showPanel:function(_1b){if(_1b=this.getPanel(_1b)){if(this.tabs){this.tabs.activate(_1b.getEl().id);}else{this.setActivePanel(_1b);}}return _1b;},getActivePanel:function(){return this.activePanel;},validateVisibility:function(){if(this.panels.getCount()<1){this.updateTitle("&#160;");this.closeBtn.hide();this.hide();}else{if(!this.isVisible()){this.show();}}},add:function(_1c){if(arguments.length>1){for(var i=0,len=arguments.length;i<len;i++){this.add(arguments[i]);}return null;}if(this.hasPanel(_1c)){this.showPanel(_1c);return _1c;}_1c.setRegion(this);this.panels.add(_1c);if(this.panels.getCount()==1&&!this.config.alwaysShowTabs){this.bodyEl.dom.appendChild(_1c.getEl().dom);if(_1c.background!==true){this.setActivePanel(_1c);}this.fireEvent("paneladded",this,_1c);return _1c;}if(!this.tabs){this.initTabs();}else{this.initPanelAsTab(_1c);}if(_1c.background!==true){this.tabs.activate(_1c.getEl().id);}this.fireEvent("paneladded",this,_1c);return _1c;},hidePanel:function(_1f){if(this.tabs&&(_1f=this.getPanel(_1f))){this.tabs.hideTab(_1f.getEl().id);}},unhidePanel:function(_20){if(this.tabs&&(_20=this.getPanel(_20))){this.tabs.unhideTab(_20.getEl().id);}},clearPanels:function(){while(this.panels.getCount()>0){this.remove(this.panels.first());}},remove:function(_21,_22){_21=this.getPanel(_21);if(!_21){return null;}var e={};this.fireEvent("beforeremove",this,_21,e);if(e.cancel===true){return null;}_22=(typeof _22!="undefined"?_22:(this.config.preservePanels===true||_21.preserve===true));var _24=_21.getId();this.panels.removeKey(_24);if(_22){document.body.appendChild(_21.getEl().dom);}if(this.tabs){this.tabs.removeTab(_21.getEl().id);}else{if(!_22){this.bodyEl.dom.removeChild(_21.getEl().dom);}}if(this.panels.getCount()==1&&this.tabs&&!this.config.alwaysShowTabs){var p=this.panels.first();var _26=document.createElement("span");_26.appendChild(p.getEl().dom);this.bodyEl.update("");this.bodyEl.dom.appendChild(p.getEl().dom);_26=null;this.updateTitle(p.getTitle());this.tabs=null;this.bodyEl.setStyle("overflow",this.config.autoScroll?"auto":"hidden");this.setActivePanel(p);}_21.setRegion(null);if(this.activePanel==_21){this.activePanel=null;}if(this.config.autoDestroy!==false&&_22!==true){try{_21.destroy();}catch(e){}}this.fireEvent("panelremoved",this,_21);return _21;},getTabs:function(){return this.tabs;},createTool:function(_27,_28){var btn=Ext.DomHelper.append(_27,{tag:"div",cls:"x-layout-tools-button",children:[{tag:"div",cls:"x-layout-tools-button-inner "+_28,html:"&#160;"}]},true);btn.addClassOnOver("x-layout-tools-button-over");return btn;}});