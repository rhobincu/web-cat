/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

(function(){var D=Ext.lib.Dom;var E=Ext.lib.Event;var A=Ext.lib.Anim;var _4={};var _5=/(-[a-z])/gi;var _6=function(m,a){return a.charAt(1).toUpperCase();};var _9=document.defaultView;Ext.Element=function(_a,_b){var _c=typeof _a=="string"?document.getElementById(_a):_a;if(!_c){return null;}if(!_b&&Ext.Element.cache[_c.id]){return Ext.Element.cache[_c.id];}this.dom=_c;this.id=_c.id||Ext.id(_c);};var El=Ext.Element;El.prototype={originalDisplay:"",visibilityMode:1,defaultUnit:"px",setVisibilityMode:function(_e){this.visibilityMode=_e;return this;},enableDisplayMode:function(_f){this.setVisibilityMode(El.DISPLAY);if(typeof _f!="undefined"){this.originalDisplay=_f;}return this;},findParent:function(_10,_11,_12){var p=this.dom,b=document.body,_15=0,dq=Ext.DomQuery,_17;_11=_11||50;if(typeof _11!="number"){_17=Ext.getDom(_11);_11=10;}while(p&&p.nodeType==1&&_15<_11&&p!=b&&p!=_17){if(dq.is(p,_10)){return _12?Ext.get(p):p;}_15++;p=p.parentNode;}return null;},is:function(_18){return Ext.DomQuery.is(this.dom,_18);},animate:function(_19,_1a,_1b,_1c,_1d){this.anim(_19,{duration:_1a,callback:_1b,easing:_1c},_1d);return this;},anim:function(_1e,opt,_20,_21,_22,cb){_20=_20||"run";opt=opt||{};var _24=Ext.lib.Anim[_20](this.dom,_1e,(opt.duration||_21)||0.35,(opt.easing||_22)||"easeOut",function(){Ext.callback(cb,this);Ext.callback(opt.callback,opt.scope||this,[this,opt]);},this);opt.anim=_24;return _24;},preanim:function(a,i){return !a[i]?false:(typeof a[i]=="object"?a[i]:{duration:a[i+1],callback:a[i+2],easing:a[i+3]});},clean:function(_27){if(this.isCleaned&&_27!==true){return this;}var ns=/\S/;var d=this.dom,n=d.firstChild,ni=-1;while(n){var nx=n.nextSibling;if(n.nodeType==3&&!ns.test(n.nodeValue)){d.removeChild(n);}else{n.nodeIndex=++ni;}n=nx;}this.isCleaned=true;return this;},calcOffsetsTo:function(el){el=Ext.get(el),d=el.dom;var _2e=false;if(el.getStyle("position")=="static"){el.position("relative");_2e=true;}var x=0,y=0;var op=this.dom;while(op&&op!=d&&op.tagName!="HTML"){x+=op.offsetLeft;y+=op.offsetTop;op=op.offsetParent;}if(_2e){el.position("static");}return [x,y];},scrollIntoView:function(_32,_33){var c=Ext.getDom(_32);var el=this.dom;var o=this.calcOffsetsTo(c),l=o[0],t=o[1],b=t+el.offsetHeight,r=l+el.offsetWidth;var ch=c.clientHeight;var ct=parseInt(c.scrollTop,10);var cl=parseInt(c.scrollLeft,10);var cb=ct+ch;var cr=cl+c.clientWidth;if(t<ct){c.scrollTop=t;}else{if(b>cb){c.scrollTop=b-ch;}}if(_33!==false){if(l<cl){c.scrollLeft=l;}else{if(r>cr){c.scrollLeft=r-c.clientWidth;}}}return this;},scrollChildIntoView:function(_40){Ext.fly(_40,"_scrollChildIntoView").scrollIntoView(this);},autoHeight:function(_41,_42,_43,_44){var _45=this.getHeight();this.clip();this.setHeight(1);setTimeout(function(){var _46=parseInt(this.dom.scrollHeight,10);if(!_41){this.setHeight(_46);this.unclip();if(typeof _43=="function"){_43();}}else{this.setHeight(_45);this.setHeight(_46,_41,_42,function(){this.unclip();if(typeof _43=="function"){_43();}}.createDelegate(this),_44);}}.createDelegate(this),0);return this;},contains:function(el){if(!el){return false;}return D.isAncestor(this.dom,el.dom?el.dom:el);},isVisible:function(_48){var vis=!(this.getStyle("visibility")=="hidden"||this.getStyle("display")=="none");if(_48!==true||!vis){return vis;}var p=this.dom.parentNode;while(p&&p.tagName.toLowerCase()!="body"){if(!Ext.fly(p,"_isVisible").isVisible()){return false;}p=p.parentNode;}return true;},select:function(_4b,_4c){return El.select("#"+Ext.id(this.dom)+" "+_4b,_4c);},query:function(_4d,_4e){return Ext.DomQuery.select(_4d,this.dom);},child:function(_4f,_50){var n=Ext.DomQuery.selectNode(_4f,this.dom);return _50?n:Ext.get(n);},initDD:function(_52,_53,_54){var dd=new Ext.dd.DD(Ext.id(this.dom),_52,_53);return Ext.apply(dd,_54);},initDDProxy:function(_56,_57,_58){var dd=new Ext.dd.DDProxy(Ext.id(this.dom),_56,_57);return Ext.apply(dd,_58);},initDDTarget:function(_5a,_5b,_5c){var dd=new Ext.dd.DDTarget(Ext.id(this.dom),_5a,_5b);return Ext.apply(dd,_5c);},setVisible:function(_5e,_5f){if(!_5f||!A){if(this.visibilityMode==El.DISPLAY){this.setDisplayed(_5e);}else{this.fixDisplay();this.dom.style.visibility=_5e?"visible":"hidden";}}else{var dom=this.dom;var _61=this.visibilityMode;if(_5e){this.setOpacity(0.01);this.setVisible(true);}this.anim({opacity:{to:(_5e?1:0)}},this.preanim(arguments,1),null,0.35,"easeIn",function(){if(!_5e){if(_61==El.DISPLAY){dom.style.display="none";}else{dom.style.visibility="hidden";}Ext.get(dom).setOpacity(1);}});}return this;},isDisplayed:function(){return this.getStyle("display")!="none";},toggle:function(_62){this.setVisible(!this.isVisible(),this.preanim(arguments,0));return this;},setDisplayed:function(_63){if(typeof _63=="boolean"){_63=_63?this.originalDisplay:"none";}this.setStyle("display",_63);return this;},focus:function(){try{this.dom.focus();}catch(e){}return this;},blur:function(){try{this.dom.blur();}catch(e){}return this;},addClass:function(_64){if(_64 instanceof Array){for(var i=0,len=_64.length;i<len;i++){this.addClass(_64[i]);}}else{if(_64&&!this.hasClass(_64)){this.dom.className=this.dom.className+" "+_64;}}return this;},radioClass:function(_67){var _68=this.dom.parentNode.childNodes;for(var i=0;i<_68.length;i++){var s=_68[i];if(s.nodeType==1){Ext.get(s).removeClass(_67);}}this.addClass(_67);return this;},removeClass:function(_6b){if(!_6b){return this;}if(_6b instanceof Array){for(var i=0,len=_6b.length;i<len;i++){this.removeClass(_6b[i]);}}else{var re=new RegExp("(?:^|\\s+)"+_6b+"(?:\\s+|$)","g");var c=this.dom.className;if(re.test(c)){this.dom.className=c.replace(re," ");}}return this;},toggleClass:function(_70){if(this.hasClass(_70)){this.removeClass(_70);}else{this.addClass(_70);}return this;},hasClass:function(_71){return _71&&(" "+this.dom.className+" ").indexOf(" "+_71+" ")!=-1;},replaceClass:function(_72,_73){this.removeClass(_72);this.addClass(_73);return this;},getStyle:function(){return _9&&_9.getComputedStyle?function(_74){var el=this.dom,v,cs,_78;if(_74=="float"){_74="cssFloat";}if(v=el.style[_74]){return v;}if(cs=_9.getComputedStyle(el,"")){if(!(_78=_4[_74])){_78=_4[_74]=_74.replace(_5,_6);}return cs[_78];}return null;}:function(_79){var el=this.dom,v,cs,_7d;if(_79=="opacity"){if(typeof el.filter=="string"){var fv=parseFloat(el.filter.match(/alpha\(opacity=(.*)\)/i)[1]);if(!isNaN(fv)){return fv?fv/100:0;}}return 1;}else{if(_79=="float"){_79="styleFloat";}}if(!(_7d=_4[_79])){_7d=_4[_79]=_79.replace(_5,_6);}if(v=el.style[_7d]){return v;}if(cs=el.currentStyle){return cs[_7d];}return null;};}(),setStyle:function(_7f,_80){if(typeof _7f=="string"){var _81;if(!(_81=_4[_7f])){_81=_4[_7f]=_7f.replace(_5,_6);}if(name=="opacity"){this.setOpacity(_80);}else{this.dom.style[_81]=_80;}}else{for(var _82 in _7f){if(typeof _7f[_82]!="function"){this.setStyle(_82,_7f[_82]);}}}return this;},applyStyles:function(_83){Ext.DomHelper.applyStyles(this.dom,_83);return this;},getX:function(){return D.getX(this.dom);},getY:function(){return D.getY(this.dom);},getXY:function(){return D.getXY(this.dom);},setX:function(x,_85){if(!_85||!A){D.setX(this.dom,x);}else{this.setXY([x,this.getY()],this.preanim(arguments,1));}return this;},setY:function(y,_87){if(!_87||!A){D.setY(this.dom,y);}else{this.setXY([this.getX(),y],this.preanim(arguments,1));}return this;},setLeft:function(_88){this.setStyle("left",this.addUnits(_88));return this;},setTop:function(top){this.setStyle("top",this.addUnits(top));return this;},setRight:function(_8a){this.setStyle("right",this.addUnits(_8a));return this;},setBottom:function(_8b){this.setStyle("bottom",this.addUnits(_8b));return this;},setXY:function(pos,_8d){if(!_8d||!A){D.setXY(this.dom,pos);}else{this.anim({points:{to:pos}},this.preanim(arguments,1),"motion");}return this;},setLocation:function(x,y,_90){this.setXY([x,y],this.preanim(arguments,2));return this;},moveTo:function(x,y,_93){this.setXY([x,y],this.preanim(arguments,2));return this;},getRegion:function(){return D.getRegion(this.dom);},getHeight:function(_94){var h=this.dom.offsetHeight||0;return _94!==true?h:h-this.getBorderWidth("tb")-this.getPadding("tb");},getWidth:function(_96){var w=this.dom.offsetWidth||0;return _96!==true?w:w-this.getBorderWidth("lr")-this.getPadding("lr");},getComputedHeight:function(){var h=Math.max(this.dom.offsetHeight,this.dom.clientHeight);if(!h){h=parseInt(this.getStyle("height"),10)||0;if(!this.isBorderBox()){h+=this.getFrameWidth("tb");}}return h;},getComputedWidth:function(){var w=Math.max(this.dom.offsetWidth,this.dom.clientWidth);if(!w){w=parseInt(this.getStyle("width"),10)||0;if(!this.isBorderBox()){w+=this.getFrameWidth("lr");}}return w;},getSize:function(_9a){return {width:this.getWidth(_9a),height:this.getHeight(_9a)};},getValue:function(_9b){return _9b?parseInt(this.dom.value,10):this.dom.value;},adjustWidth:function(_9c){if(typeof _9c=="number"){if(this.autoBoxAdjust&&!this.isBorderBox()){_9c-=(this.getBorderWidth("lr")+this.getPadding("lr"));}if(_9c<0){_9c=0;}}return _9c;},adjustHeight:function(_9d){if(typeof _9d=="number"){if(this.autoBoxAdjust&&!this.isBorderBox()){_9d-=(this.getBorderWidth("tb")+this.getPadding("tb"));}if(_9d<0){_9d=0;}}return _9d;},setWidth:function(_9e,_9f){_9e=this.adjustWidth(_9e);if(!_9f||!A){this.dom.style.width=this.addUnits(_9e);}else{this.anim({width:{to:_9e}},this.preanim(arguments,1));}return this;},setHeight:function(_a0,_a1){_a0=this.adjustHeight(_a0);if(!_a1||!A){this.dom.style.height=this.addUnits(_a0);}else{this.anim({height:{to:_a0}},this.preanim(arguments,1));}return this;},setSize:function(_a2,_a3,_a4){if(typeof _a2=="object"){_a3=_a2.height;_a2=_a2.width;}_a2=this.adjustWidth(_a2);_a3=this.adjustHeight(_a3);if(!_a4||!A){this.dom.style.width=this.addUnits(_a2);this.dom.style.height=this.addUnits(_a3);}else{this.anim({width:{to:_a2},height:{to:_a3}},this.preanim(arguments,2));}return this;},setBounds:function(x,y,_a7,_a8,_a9){if(!_a9||!A){this.setSize(_a7,_a8);this.setLocation(x,y);}else{_a7=this.adjustWidth(_a7);_a8=this.adjustHeight(_a8);this.anim({points:{to:[x,y]},width:{to:_a7},height:{to:_a8}},this.preanim(arguments,4),"motion");}return this;},setRegion:function(_aa,_ab){this.setBounds(_aa.left,_aa.top,_aa.right-_aa.left,_aa.bottom-_aa.top,this.preanim(arguments,1));return this;},addListener:function(_ac,fn,_ae,_af){Ext.EventManager.on(this.dom,_ac,fn,_ae||this,_af);},removeListener:function(_b0,fn){Ext.EventManager.removeListener(this.dom,_b0,fn);return this;},removeAllListeners:function(){E.purgeElement(this.dom);return this;},relayEvent:function(_b2,_b3){this.on(_b2,function(e){_b3.fireEvent(_b2,e);});},setOpacity:function(_b5,_b6){if(!_b6||!A){var s=this.dom.style;if(Ext.isIE){s.zoom=1;s.filter=(s.filter||"").replace(/alpha\([^\)]*\)/gi,"")+(_b5==1?"":"alpha(opacity="+_b5*100+")");}else{s.opacity=_b5;}}else{this.anim({opacity:{to:_b5}},this.preanim(arguments,1),null,0.35,"easeIn");}return this;},getLeft:function(_b8){if(!_b8){return this.getX();}else{return parseInt(this.getStyle("left"),10)||0;}},getRight:function(_b9){if(!_b9){return this.getX()+this.getWidth();}else{return (this.getLeft(true)+this.getWidth())||0;}},getTop:function(_ba){if(!_ba){return this.getY();}else{return parseInt(this.getStyle("top"),10)||0;}},getBottom:function(_bb){if(!_bb){return this.getY()+this.getHeight();}else{return (this.getTop(true)+this.getHeight())||0;}},position:function(pos,_bd,x,y){if(!pos){if(this.getStyle("position")=="static"){this.setStyle("position","relative");}}else{this.setStyle("position",pos);}if(_bd){this.setStyle("z-index",_bd);}if(x!==undefined&&y!==undefined){this.setXY([x,y]);}else{if(x!==undefined){this.setX(x);}else{if(y!==undefined){this.setY(y);}}}},clearPositioning:function(_c0){_c0=_c0||"";this.setStyle({"left":_c0,"right":_c0,"top":_c0,"bottom":_c0,"z-index":"","position":"static"});return this;},getPositioning:function(){var l=this.getStyle("left");var t=this.getStyle("top");return {"position":this.getStyle("position"),"left":l,"right":l?"":this.getStyle("right"),"top":t,"bottom":t?"":this.getStyle("bottom"),"z-index":this.getStyle("z-index")};},getBorderWidth:function(_c3){return this.addStyles(_c3,El.borders);},getPadding:function(_c4){return this.addStyles(_c4,El.paddings);},setPositioning:function(pc){this.applyStyles(pc);if(pc.right=="auto"){this.dom.style.right="";}if(pc.bottom=="auto"){this.dom.style.bottom="";}return this;},fixDisplay:function(){if(this.getStyle("display")=="none"){this.setStyle("visibility","hidden");this.setStyle("display",this.originalDisplay);if(this.getStyle("display")=="none"){this.setStyle("display","block");}}},setLeftTop:function(_c6,top){this.dom.style.left=this.addUnits(_c6);this.dom.style.top=this.addUnits(top);return this;},move:function(_c8,_c9,_ca){var xy=this.getXY();_c8=_c8.toLowerCase();switch(_c8){case "l":case "left":this.moveTo(xy[0]-_c9,xy[1],this.preanim(arguments,2));break;case "r":case "right":this.moveTo(xy[0]+_c9,xy[1],this.preanim(arguments,2));break;case "t":case "top":case "up":this.moveTo(xy[0],xy[1]-_c9,this.preanim(arguments,2));break;case "b":case "bottom":case "down":this.moveTo(xy[0],xy[1]+_c9,this.preanim(arguments,2));break;}return this;},clip:function(){if(!this.isClipped){this.isClipped=true;this.originalClip={"o":this.getStyle("overflow"),"x":this.getStyle("overflow-x"),"y":this.getStyle("overflow-y")};this.setStyle("overflow","hidden");this.setStyle("overflow-x","hidden");this.setStyle("overflow-y","hidden");}return this;},unclip:function(){if(this.isClipped){this.isClipped=false;var o=this.originalClip;if(o.o){this.setStyle("overflow",o.o);}if(o.x){this.setStyle("overflow-x",o.x);}if(o.y){this.setStyle("overflow-y",o.y);}}return this;},getAnchorXY:function(_cd,_ce,s){var w,h,vp=false;if(!s){var d=this.dom;if(d==document.body||d==document){vp=true;w=D.getViewWidth();h=D.getViewHeight();}else{w=this.getWidth();h=this.getHeight();}}else{w=s.width;h=s.height;}var x=0,y=0,r=Math.round;switch((_cd||"tl").toLowerCase()){case "c":x=r(w*0.5);y=r(h*0.5);break;case "t":x=r(w*0.5);y=0;break;case "l":x=0;y=r(h*0.5);break;case "r":x=w;y=r(h*0.5);break;case "b":x=r(w*0.5);y=h;break;case "tl":x=0;y=0;break;case "bl":x=0;y=h;break;case "br":x=w;y=h;break;case "tr":x=w;y=0;break;}if(_ce===true){return [x,y];}if(vp){var sc=this.getScroll();return [x+sc.left,y+sc.top];}var o=this.getXY();return [x+o[0],y+o[1]];},getAlignToXY:function(el,p,o){el=Ext.get(el),d=this.dom;if(!el.dom){throw "Element.alignTo with an element that doesn't exist";}var c=false;var p1="",p2="";o=o||[0,0];if(!p){p="tl-bl";}else{if(p=="?"){p="tl-bl?";}else{if(p.indexOf("-")==-1){p="tl-"+p;}}}p=p.toLowerCase();var m=p.match(/^([a-z]+)-([a-z]+)(\?)?$/);if(!m){throw "Element.alignTo with an invalid alignment "+p;}p1=m[1],p2=m[2],c=m[3]?true:false;var a1=this.getAnchorXY(p1,true);var a2=el.getAnchorXY(p2,false);var x=a2[0]-a1[0]+o[0];var y=a2[1]-a1[1]+o[1];if(c){var w=this.getWidth(),h=this.getHeight(),r=el.getRegion();var dw=D.getViewWidth()-5,dh=D.getViewHeight()-5;var p1y=p1.charAt(0),p1x=p1.charAt(p1.length-1);var p2y=p2.charAt(0),p2x=p2.charAt(p2.length-1);var _ed=((p1y=="t"&&p2y=="b")||(p1y=="b"&&p2y=="t"));var _ee=((p1x=="r"&&p2x=="l")||(p1x=="l"&&p2x=="r"));var doc=document;var _f0=(doc.documentElement.scrollLeft||doc.body.scrollLeft||0)+5;var _f1=(doc.documentElement.scrollTop||doc.body.scrollTop||0)+5;if((x+w)>dw){x=_ee?r.left-w:dw-w;}if(x<_f0){x=_ee?r.right:_f0;}if((y+h)>dh){y=_ed?r.top-h:dh-h;}if(y<_f1){y=_ed?r.bottom:_f1;}}return [x,y];},alignTo:function(_f2,_f3,_f4,_f5){var xy=this.getAlignToXY(_f2,_f3,_f4);this.setXY(xy,this.preanim(arguments,3));return this;},anchorTo:function(el,_f8,_f9,_fa,_fb){var _fc=function(){this.alignTo(el,_f8,_f9,_fa);};Ext.EventManager.onWindowResize(_fc,this);var tm=typeof _fb;if(tm!="undefined"){Ext.EventManager.on(window,"scroll",_fc,this,{buffer:tm=="number"?_fb:50});}_fc.call(this);},clearOpacity:function(){if(window.ActiveXObject){this.dom.style.filter="";}else{this.dom.style.opacity="";this.dom.style["-moz-opacity"]="";this.dom.style["-khtml-opacity"]="";}return this;},hide:function(_fe){this.setVisible(false,this.preanim(arguments,0));return this;},show:function(_ff){this.setVisible(true,this.preanim(arguments,0));return this;},addUnits:function(size){if(size===""||size=="auto"||typeof size=="undefined"){return size;}if(typeof size=="number"||!El.unitPattern.test(size)){return size+this.defaultUnit;}return size;},beginMeasure:function(){var el=this.dom;if(el.offsetWidth||el.offsetHeight){return this;}var _102=[];var p=this.dom,b=document.body;while((!el.offsetWidth&&!el.offsetHeight)&&p&&p.tagName&&p!=b){var pe=Ext.get(p);if(pe.getStyle("display")=="none"){_102.push({el:p,visibility:pe.getStyle("visibility")});p.style.visibility="hidden";p.style.display="block";}p=p.parentNode;}this._measureChanged=_102;return this;},endMeasure:function(){var _106=this._measureChanged;if(_106){for(var i=0,len=_106.length;i<len;i++){var r=_106[i];r.el.style.visibility=r.visibility;r.el.style.display="none";}this._measureChanged=null;}return this;},update:function(html,_10b,_10c){if(typeof html=="undefined"){html="";}if(_10b!==true){this.dom.innerHTML=html;if(typeof _10c=="function"){_10c();}return this;}var id=Ext.id();var dom=this.dom;html+="<span id=\""+id+"\"></span>";E.onAvailable(id,function(){var hd=document.getElementsByTagName("head")[0];var re=/(?:<script([^>]*)?>)((\n|\r|.)*?)(?:<\/script>)/img;var _111=/\ssrc=([\'\"])(.*?)\1/i;var _112;while(_112=re.exec(html)){var _113=_112[1].match(_111);if(_113&&_113[2]){var s=document.createElement("script");s.src=_113[2];hd.appendChild(s);}else{if(_112[2]&&_112[2].length>0){eval(_112[2]);}}}var el=document.getElementById(id);if(el){el.parentNode.removeChild(el);}if(typeof _10c=="function"){_10c();}});dom.innerHTML=html.replace(/(?:<script.*?>)((\n|\r|.)*?)(?:<\/script>)/img,"");return this;},load:function(){var um=this.getUpdateManager();um.update.apply(um,arguments);return this;},getUpdateManager:function(){if(!this.updateManager){this.updateManager=new Ext.UpdateManager(this);}return this.updateManager;},unselectable:function(){this.dom.unselectable="on";this.swallowEvent("selectstart",true);this.applyStyles("-moz-user-select:none;-khtml-user-select:none;");this.addClass("x-unselectable");return this;},getCenterXY:function(){return this.getAlignToXY(document,"c-c");},center:function(_117){this.alignTo(_117||document,"c-c");return this;},isBorderBox:function(){return _118[this.dom.tagName.toLowerCase()]||Ext.isBorderBox;},getBox:function(_119,_11a){var xy;if(!_11a){xy=this.getXY();}else{var left=parseInt(this.getStyle("left"),10)||0;var top=parseInt(this.getStyle("top"),10)||0;xy=[left,top];}var el=this.dom,w=el.offsetWidth,h=el.offsetHeight,bx;if(!_119){bx={x:xy[0],y:xy[1],width:w,height:h};}else{var l=this.getBorderWidth("l")+this.getPadding("l");var r=this.getBorderWidth("r")+this.getPadding("r");var t=this.getBorderWidth("t")+this.getPadding("t");var b=this.getBorderWidth("b")+this.getPadding("b");bx={x:xy[0]+l,y:xy[1]+t,width:w-(l+r),height:h-(t+b)};}bx.right=bx.x+bx.width;bx.bottom=bx.y+bx.height;return bx;},getFrameWidth:function(_126){return this.getPadding(_126)+this.getBorderWidth(_126);},setBox:function(box,_128,_129){var w=box.width,h=box.height;if((_128&&!this.autoBoxAdjust)&&!this.isBorderBox()){w-=(this.getBorderWidth("lr")+this.getPadding("lr"));h-=(this.getBorderWidth("tb")+this.getPadding("tb"));}this.setBounds(box.x,box.y,w,h,this.preanim(arguments,2));return this;},repaint:function(){var dom=this.dom;this.addClass("x-repaint");setTimeout(function(){Ext.get(dom).removeClass("x-repaint");},1);return this;},getMargins:function(side){if(!side){return {top:parseInt(this.getStyle("margin-top"),10)||0,left:parseInt(this.getStyle("margin-left"),10)||0,bottom:parseInt(this.getStyle("margin-bottom"),10)||0,right:parseInt(this.getStyle("margin-right"),10)||0};}else{return this.addStyles(side,El.margins);}},addStyles:function(_12e,_12f){var val=0;for(var i=0,len=_12e.length;i<len;i++){var w=parseInt(this.getStyle(_12f[_12e.charAt(i)]),10);if(!isNaN(w)){val+=w;}}return val;},createProxy:function(_134,_135,_136){if(_135){_135=Ext.getDom(_135);}else{_135=document.body;}_134=typeof _134=="object"?_134:{tag:"div",cls:_134};var _137=Ext.DomHelper.append(_135,_134,true);if(_136){_137.setBox(this.getBox());}return _137;},mask:function(){if(this.getStyle("position")=="static"){this.setStyle("position","relative");}if(!this._mask){this._mask=Ext.DomHelper.append(this.dom,{tag:"div",cls:"ext-el-mask"},true);}this.addClass("x-masked");this._mask.setDisplayed(true);return this._mask;},unmask:function(_138){if(this._mask){if(_138===true){this._mask.remove();delete this._mask;}else{this._mask.setDisplayed(false);}}this.removeClass("x-masked");},isMasked:function(){return this._mask&&this._mask.isVisible();},createShim:function(){var _139={tag:"iframe",frameBorder:"no",cls:"yiframe-shim",style:"position:absolute;visibility:hidden;left:0;top:0;overflow:hidden;",src:Ext.SSL_SECURE_URL};var shim=Ext.DomHelper.insertBefore(this.dom,_139,true);shim.setOpacity(0.01);shim.setBox(this.getBox());return shim;},remove:function(){if(this.dom.parentNode){this.dom.parentNode.removeChild(this.dom);}delete El.cache[this.dom.id];},addClassOnOver:function(_13b){this.on("mouseover",function(){Ext.fly(this,"_internal").addClass(_13b);},this.dom);var _13c=function(){Ext.fly(this,"_internal").removeClass(_13b);};this.on("mouseout",_13c,this.dom);return this;},addClassOnFocus:function(_13d){this.on("focus",function(){Ext.fly(this,"_internal").addClass(_13d);},this.dom);this.on("blur",function(){Ext.fly(this,"_internal").removeClass(_13d);},this.dom);return this;},addClassOnClick:function(_13e){var dom=this.dom;this.on("mousedown",function(){Ext.fly(dom,"_internal").addClass(_13e);var d=Ext.get(document);var fn=function(){Ext.fly(dom,"_internal").removeClass(_13e);d.removeListener("mouseup",fn);};d.on("mouseup",fn);});return this;},swallowEvent:function(_142,_143){var fn=function(e){e.stopPropagation();if(_143){e.preventDefault();}};if(_142 instanceof Array){for(var i=0,len=_142.length;i<len;i++){this.on(_142[i],fn);}return this;}this.on(_142,fn);return this;},fitToParent:function(_148,_149){var p=Ext.get(_149||this.dom.parentNode);this.setSize(p.getComputedWidth()-p.getFrameWidth("lr"),p.getComputedHeight()-p.getFrameWidth("tb"));if(_148===true){Ext.EventManager.onWindowResize(this.fitToParent.createDelegate(this,[]));}return this;},getNextSibling:function(){var n=this.dom.nextSibling;while(n&&n.nodeType!=1){n=n.nextSibling;}return n;},getPrevSibling:function(){var n=this.dom.previousSibling;while(n&&n.nodeType!=1){n=n.previousSibling;}return n;},appendChild:function(el){el=Ext.get(el);el.appendTo(this);return this;},createChild:function(_14e,_14f,_150){_14e=_14e||{tag:"div"};if(_14f){return Ext.DomHelper.insertBefore(_14f,_14e,_150!==true);}return Ext.DomHelper.append(this.dom,_14e,_150!==true);},appendTo:function(el){el=Ext.getDom(el);el.appendChild(this.dom);return this;},insertBefore:function(el){el=Ext.getDom(el);el.parentNode.insertBefore(this.dom,el);return this;},insertAfter:function(el){el=Ext.getDom(el);el.parentNode.insertBefore(this.dom,el.nextSibling);return this;},insertFirst:function(el,_155){el=el||{};if(typeof el=="object"&&!el.nodeType){return this.createChild(el,this.dom.firstChild,_155);}else{el=Ext.getDom(el);this.dom.insertBefore(el,this.domain.firstChild);return !_155?Ext.get(el):el;}},insertSibling:function(el,_157,_158){_157=_157?_157.toLowerCase():"before";el=el||{};var rt,_15a=_157=="before"?this.dom:this.dom.nextSibling;if(typeof el=="object"&&!el.nodeType){if(_157=="after"&&!this.dom.nextSibling){rt=Ext.DomHelper.append(this.dom.parentNode,el,!_158);}else{rt=Ext.DomHelper[_157=="after"?"insertAfter":"insertBefore"](this.dom,el,!_158);}}else{rt=this.dom.parentNode.insertBefore(Ext.getDom(el),_157=="before"?this.dom:this.dom.nextSibling);if(!_158){rt=Ext.get(rt);}}return rt;},wrap:function(_15b,_15c){if(!_15b){_15b={tag:"div"};}var _15d=Ext.DomHelper.insertBefore(this.dom,_15b,!_15c);_15d.dom?_15d.dom.appendChild(this.dom):_15d.appendChild(this.dom);return _15d;},replace:function(el){el=Ext.get(el);this.insertBefore(el);el.remove();return this;},insertHtml:function(_15f,html){return Ext.DomHelper.insertHtml(_15f,this.dom,html);},set:function(o){var el=this.dom;var _163=el.setAttribute?true:false;for(var attr in o){if(attr=="style"||typeof o[attr]=="function"){continue;}if(attr=="cls"){el.className=o["cls"];}else{if(_163){el.setAttribute(attr,o[attr]);}else{el[attr]=o[attr];}}}Ext.DomHelper.applyStyles(el,o.style);return this;},addKeyListener:function(key,fn,_167){var _168;if(typeof key!="object"||key instanceof Array){_168={key:key,fn:fn,scope:_167};}else{_168={key:key.key,shift:key.shift,ctrl:key.ctrl,alt:key.alt,fn:fn,scope:_167};}return new Ext.KeyMap(this,_168);},addKeyMap:function(_169){return new Ext.KeyMap(this,_169);},isScrollable:function(){var dom=this.dom;return dom.scrollHeight>dom.clientHeight||dom.scrollWidth>dom.clientWidth;},scrollTo:function(side,_16c,_16d){var prop=side.toLowerCase()=="left"?"scrollLeft":"scrollTop";if(!_16d||!A){this.dom[prop]=_16c;}else{var to=prop=="scrollLeft"?[_16c,this.dom.scrollTop]:[this.dom.scrollLeft,_16c];this.anim({scroll:{"to":to}},this.preanim(arguments,2),"scroll");}return this;},scroll:function(_170,_171,_172){if(!this.isScrollable()){return;}var el=this.dom;var l=el.scrollLeft,t=el.scrollTop;var w=el.scrollWidth,h=el.scrollHeight;var cw=el.clientWidth,ch=el.clientHeight;_170=_170.toLowerCase();var _17a=false;var a=this.preanim(arguments,2);switch(_170){case "l":case "left":if(w-l>cw){var v=Math.min(l+_171,w-cw);this.scrollTo("left",v,a);_17a=true;}break;case "r":case "right":if(l>0){var v=Math.max(l-_171,0);this.scrollTo("left",v,a);_17a=true;}break;case "t":case "top":case "up":if(t>0){var v=Math.max(t-_171,0);this.scrollTo("top",v,a);_17a=true;}break;case "b":case "bottom":case "down":if(h-t>ch){var v=Math.min(t+_171,h-ch);this.scrollTo("top",v,a);_17a=true;}break;}return _17a;},translatePoints:function(x,y){if(x instanceof Array){y=x[1];x=x[0];}var p=this.getStyle("position");var o=this.getXY();var l=parseInt(this.getStyle("left"),10);var t=parseInt(this.getStyle("top"),10);if(isNaN(l)){l=(p=="relative")?0:this.dom.offsetLeft;}if(isNaN(t)){t=(p=="relative")?0:this.dom.offsetTop;}return {left:(x-o[0]+l),top:(y-o[1]+t)};},getScroll:function(){var d=this.dom,doc=document;if(d==doc||d==doc.body){var l=window.pageXOffset||doc.documentElement.scrollLeft||doc.body.scrollLeft||0;var t=window.pageYOffset||doc.documentElement.scrollTop||doc.body.scrollTop||0;return {left:l,top:t};}else{return {left:d.scrollLeft,top:d.scrollTop};}},getColor:function(attr,_188,_189){var v=this.getStyle(attr);if(!v||v=="transparent"||v=="inherit"){return _188;}var _18b=typeof _189=="undefined"?"#":_189;if(v.substr(0,4)=="rgb("){var rvs=v.slice(4,v.length-1).split(",");for(var i=0;i<3;i++){var h=parseInt(rvs[i]).toString(16);if(h<16){h="0"+h;}_18b+=h;}}else{if(v.substr(0,1)=="#"){if(v.length==4){for(var i=1;i<4;i++){var c=v.charAt(i);_18b+=c+c;}}else{if(v.length==7){_18b+=v.slice(1,6);}}}}return (_18b.length>5?_18b.toLowerCase():_188);},boxWrap:function(cls){cls=cls||"x-box";var el=Ext.get(this.insertHtml("beforeBegin",String.format("<div class=\"{0}\"><div class=\"{0}-tl\"><div class=\"{0}-tr\"><div class=\"{0}-tc\"></div></div></div><div class=\"{0}-ml\"><div class=\"{0}-mr\"><div class=\"{0}-mc\"></div></div></div><div class=\"{0}-bl\"><div class=\"{0}-br\"><div class=\"{0}-bc\"></div></div></div></div>",cls)));el.child("."+cls+"-mc").dom.appendChild(this.dom);return el;},getAttributeNS:Ext.isIE?function(ns,name){var d=this.dom;var type=typeof d[ns+":"+name];if(type!="undefined"&&type!="unknown"){return d[ns+":"+name];}return d[name];}:function(ns,name){var d=this.dom;return d.getAttributeNS(ns,name)||d.getAttribute(ns+":"+name)||d.getAttribute(name)||d[name];}};var ep=El.prototype;ep.on=ep.addListener;ep.mon=ep.addListener;ep.un=ep.removeListener;ep.autoBoxAdjust=true;ep.autoDisplayMode=true;El.unitPattern=/\d+(px|em|%|en|ex|pt|in|cm|mm|pc)$/i;El.VISIBILITY=1;El.DISPLAY=2;El.borders={l:"border-left-width",r:"border-right-width",t:"border-top-width",b:"border-bottom-width"};El.paddings={l:"padding-left",r:"padding-right",t:"padding-top",b:"padding-bottom"};El.margins={l:"margin-left",r:"margin-right",t:"margin-top",b:"margin-bottom"};El.cache={};var _19a;El.get=function(el){var ex,elm,id;if(!el){return null;}if(typeof el=="string"){if(!(elm=document.getElementById(el))){return null;}if(ex=El.cache[el]){ex.dom=elm;}else{ex=El.cache[el]=new El(elm);}return ex;}else{if(el.tagName){if(!(id=el.id)){id=Ext.id(el);}if(ex=El.cache[id]){ex.dom=el;}else{ex=El.cache[id]=new El(el);}return ex;}else{if(el instanceof El){if(el!=_19a){el.dom=document.getElementById(el.id)||el.dom;El.cache[el.id]=el;}return el;}else{if(el.isComposite){return el;}else{if(el instanceof Array){return El.select(el);}else{if(el==document){if(!_19a){var f=function(){};f.prototype=El.prototype;_19a=new f();_19a.dom=document;}return _19a;}}}}}}return null;};El.Flyweight=function(dom){this.dom=dom;};El.Flyweight.prototype=El.prototype;El._flyweights={};El.fly=function(el,_1a2){_1a2=_1a2||"_global";el=Ext.getDom(el);if(!El._flyweights[_1a2]){El._flyweights[_1a2]=new El.Flyweight();}El._flyweights[_1a2].dom=el;return El._flyweights[_1a2];};Ext.get=El.get;Ext.fly=El.fly;var _118=Ext.isStrict?{select:1}:{input:1,select:1,textarea:1};if(Ext.isIE||Ext.isGecko){_118["button"]=1;}if(Ext.isGecko&&!Ext.isStrict){_118["textarea"]=0;}var ml;El.measureText=function(el,text,_1a6){if(!ml){ml=new El(document.createElement("div"));document.body.appendChild(ml.dom);ml.position("absolute");ml.setLeftTop(-1000,-1000);ml.hide();}el=Ext.fly(el);ml.setStyle({"font-size":el.getStyle("font-size"),"font-style":el.getStyle("font-style"),"font-weight":el.getStyle("font-weight"),"font-family":el.getStyle("font-family")});if(_1a6){mi.setWidth(_1a6);}ml.update(text);var s=ml.getSize();ml.update("");if(_1a6){mi.setWidth("auto");}return s;};})();