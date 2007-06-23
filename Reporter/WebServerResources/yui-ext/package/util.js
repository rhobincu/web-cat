/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

Ext.util.DelayedTask=function(fn,_2,_3){var id=null,d,t;var _7=function(){var _8=new Date().getTime();if(_8-t>=d){clearInterval(id);id=null;fn.apply(_2,_3||[]);}};this.delay=function(_9,_a,_b,_c){if(id&&_9!=d){this.cancel();}d=_9;t=new Date().getTime();fn=_a||fn;_2=_b||_2;_3=_c||_3;if(!id){id=setInterval(_7,d);}};this.cancel=function(){if(id){clearInterval(id);id=null;}};};

Ext.util.MixedCollection=function(_1,_2){this.items=[];this.map={};this.keys=[];this.length=0;this.events={"clear":true,"add":true,"replace":true,"remove":true,"sort":true};this.allowFunctions=_1===true;if(_2){this.getKey=_2;}};Ext.extend(Ext.util.MixedCollection,Ext.util.Observable,{allowFunctions:false,add:function(_3,o){if(arguments.length==1){o=arguments[0];_3=this.getKey(o);}if(typeof _3=="undefined"||_3===null){this.length++;this.items.push(o);this.keys.push(null);}else{var _5=this.map[_3];if(_5){return this.replace(_3,o);}this.length++;this.items.push(o);this.map[_3]=o;this.keys.push(_3);}this.fireEvent("add",this.length-1,o,_3);return o;},getKey:function(o){return o.id;},replace:function(_7,o){if(arguments.length==1){o=arguments[0];_7=this.getKey(o);}var _9=this.item(_7);if(typeof _7=="undefined"||_7===null||typeof _9=="undefined"){return this.add(_7,o);}var _a=this.indexOfKey(_7);this.items[_a]=o;this.map[_7]=o;this.fireEvent("replace",_7,_9,o);return o;},addAll:function(_b){if(arguments.length>1||_b instanceof Array){var _c=arguments.length>1?arguments:_b;for(var i=0,_e=_c.length;i<_e;i++){this.add(_c[i]);}}else{for(var _f in _b){if(this.allowFunctions||typeof _b[_f]!="function"){this.add(_b[_f],_f);}}}},each:function(fn,_11){var _12=[].concat(this.items);for(var i=0,len=_12.length;i<len;i++){if(fn.call(_11||_12[i],_12[i])===false){break;}}},eachKey:function(fn,_16){for(var i=0,len=this.keys.length;i<len;i++){fn.call(_16||window,this.keys[i],this.items[i]);}},find:function(fn,_1a){for(var i=0,len=this.items.length;i<len;i++){if(fn.call(_1a||window,this.items[i],this.keys[i])){return this.items[i];}}return null;},insert:function(_1d,key,o){if(arguments.length==2){o=arguments[1];key=this.getKey(o);}if(_1d>=this.length){return this.add(key,o);}this.length++;this.items.splice(_1d,0,o);if(typeof key!="undefined"&&key!=null){this.map[key]=o;}this.keys.splice(_1d,0,key);this.fireEvent("add",_1d,o,key);return o;},remove:function(o){return this.removeAt(this.indexOf(o));},removeAt:function(_21){if(_21<this.length&&_21>=0){this.length--;var o=this.items[_21];this.items.splice(_21,1);var key=this.keys[_21];if(typeof key!="undefined"){delete this.map[key];}this.keys.splice(_21,1);this.fireEvent("remove",o,key);}},removeKey:function(key){return this.removeAt(this.indexOfKey(key));},getCount:function(){return this.length;},indexOf:function(o){if(!this.items.indexOf){for(var i=0,len=this.items.length;i<len;i++){if(this.items[i]==o){return i;}}return -1;}else{return this.items.indexOf(o);}},indexOfKey:function(key){if(!this.keys.indexOf){for(var i=0,len=this.keys.length;i<len;i++){if(this.keys[i]==key){return i;}}return -1;}else{return this.keys.indexOf(key);}},item:function(key){return typeof this.map[key]!="undefined"?this.map[key]:this.items[key];},itemAt:function(_2c){return this.items[_2c];},key:function(key){return this.map[key];},contains:function(o){return this.indexOf(o)!=-1;},containsKey:function(key){return typeof this.map[key]!="undefined";},clear:function(){this.length=0;this.items=[];this.keys=[];this.map={};this.fireEvent("clear");},first:function(){return this.items[0];},last:function(){return this.items[this.length-1];},_sort:function(_30,dir,fn){var dsc=String(dir).toUpperCase()=="DESC"?-1:1;fn=fn||function(a,b){return a-b;};var c=[],k=this.keys,_38=this.items;for(var i=0,len=_38.length;i<len;i++){c[c.length]={key:k[i],value:_38[i],index:i};}c.sort(function(a,b){var v=fn(a[_30],b[_30])*dsc;if(v==0){v=(a.index<b.index?-1:1);}return v;});for(var i=0,len=c.length;i<len;i++){_38[i]=c[i].value;k[i]=c[i].key;}this.fireEvent("sort",this);},sort:function(dir,fn){this._sort("value",dir,fn);},keySort:function(dir,fn){this._sort("key",dir,fn||function(a,b){return String(a).toUpperCase()-String(b).toUpperCase();});},getRange:function(_44,end){var _46=this.items;if(_46.length<1){return [];}_44=_44||0;end=Math.min(typeof end=="undefined"?this.length-1:end,this.length-1);var r=[];if(_44<=end){for(var i=_44;i<=end;i++){r[r.length]=_46[i];}}else{for(var i=_44;i>=end;i--){r[r.length]=_46[i];}}return r;},filter:function(_49,_4a){if(!_4a.exec){_4a=String(_4a);if(_4a.length==0){return this.clone();}_4a=new RegExp("^"+Ext.escapeRe(_4a),"i");}return this.filterBy(function(o){return o&&_4a.test(o[_49]);});},filterBy:function(fn,_4d){var r=new Ext.util.MixedCollection();r.getKey=this.getKey;var k=this.keys,it=this.items;for(var i=0,len=it.length;i<len;i++){if(fn.call(_4d||this,it[i],k[i])){r.add(k[i],it[i]);}}return r;},clone:function(){var r=new Ext.util.MixedCollection();var k=this.keys,it=this.items;for(var i=0,len=it.length;i<len;i++){r.add(k[i],it[i]);}r.getKey=this.getKey;return r;}});Ext.util.MixedCollection.prototype.get=Ext.util.MixedCollection.prototype.item;

Ext.util.JSON=new (function(){var _1={}.hasOwnProperty?true:false;var _2=function(n){return n<10?"0"+n:n;};var m={"\b":"\\b","\t":"\\t","\n":"\\n","\f":"\\f","\r":"\\r","\"":"\\\"","\\":"\\\\"};var _5=function(s){if(/["\\\x00-\x1f]/.test(s)){return "\""+s.replace(/([\x00-\x1f\\"])/g,function(a,b){var c=m[b];if(c){return c;}c=b.charCodeAt();return "\\u00"+Math.floor(c/16).toString(16)+(c%16).toString(16);})+"\"";}return "\""+s+"\"";};var _a=function(o){var a=["["],b,i,l=o.length,v;for(i=0;i<l;i+=1){v=o[i];switch(typeof v){case "undefined":case "function":case "unknown":break;default:if(b){a.push(",");}a.push(v===null?"null":Ext.util.JSON.encode(v));b=true;}}a.push("]");return a.join("");};var _11=function(o){return "\""+o.getFullYear()+"-"+_2(o.getMonth()+1)+"-"+_2(o.getDate())+"T"+_2(o.getHours())+":"+_2(o.getMinutes())+":"+_2(o.getSeconds())+"\"";};this.encode=function(o){if(typeof o=="undefined"||o===null){return "null";}else{if(o instanceof Array){return _a(o);}else{if(o instanceof Date){return _11(o);}else{if(typeof o=="string"){return _5(o);}else{if(typeof o=="number"){return isFinite(o)?String(o):"null";}else{if(typeof o=="boolean"){return String(o);}else{var a=["{"],b,i,v;for(i in o){if(!_1||o.hasOwnProperty(i)){v=o[i];switch(typeof v){case "undefined":case "function":case "unknown":break;default:if(b){a.push(",");}a.push(this.encode(i),":",v===null?"null":this.encode(v));b=true;}}}a.push("}");return a.join("");}}}}}}};this.decode=function(_18){return eval("("+_18+")");};})();Ext.encode=Ext.util.JSON.encode;Ext.decode=Ext.util.JSON.decode;

Ext.util.Format=function(){var _1=/^\s*(.*)\s*$/;return {ellipsis:function(_2,_3){if(_2&&_2.length>_3){return _2.substr(0,_3-3)+"...";}return _2;},undef:function(_4){return typeof _4!="undefined"?_4:"";},htmlEncode:function(_5){return !_5?_5:String(_5).replace(/&/g,"&amp;").replace(/>/g,"&gt;").replace(/</g,"&lt;").replace(/"/g,"&quot;");},trim:function(_6){return String(_6).replace(_1,"$1");},substr:function(_7,_8,_9){return String(_7).substr(_8,_9);},lowercase:function(_a){return String(_a).toLowerCase();},uppercase:function(_b){return String(_b).toUpperCase();},capitalize:function(_c){return !_c?_c:_c.charAt(0).toUpperCase()+_c.substr(1).toLowerCase();},call:function(_d,fn){if(arguments.length>2){var _f=Array.prototype.slice.call(arguments,2);_f.unshift(_d);return eval(fn).apply(window,_f);}else{return eval(fn).call(window,_d);}},usMoney:function(v){v=(Math.round((v-0)*100))/100;v=(v==Math.floor(v))?v+".00":((v*10==Math.floor(v*10))?v+"0":v);return "$"+v;},date:function(v,_12){if(!v){return "";}if(!(v instanceof Date)){v=new Date(Date.parse(v));}return v.dateFormat(_12||"m/d/Y");},dateRenderer:function(_13){return function(v){return Ext.util.Format.date(v,_13);};},stripTagsRE:/<\/?[^>]+>/gi,stripTags:function(v){return !v?v:String(v).replace(this.stripTagsRE,"");}};}();

Ext.util.CSS=function(){var _1=null;var _2=document;var _3=function(_4){var _5=function(_6){var _7=/(-[a-z])/i.exec(_6);return _6.replace(RegExp.$1,RegExp.$1.substr(1).toUpperCase());};while(_4.indexOf("-")>-1){_4=_5(_4);}return _4;};return {createStyleSheet:function(_8){var ss;if(Ext.isIE){ss=_2.createStyleSheet();ss.cssText=_8;}else{var _a=_2.getElementsByTagName("head")[0];var _b=_2.createElement("style");_b.setAttribute("type","text/css");try{_b.appendChild(_2.createTextNode(_8));}catch(e){_b.cssText=_8;}_a.appendChild(_b);ss=_b.styleSheet?_b.styleSheet:(_b.sheet||_2.styleSheets[_2.styleSheets.length-1]);}this.cacheStyleSheet(ss);return ss;},removeStyleSheet:function(id){var _d=_2.getElementById(id);if(_d){_d.parentNode.removeChild(_d);}},swapStyleSheet:function(id,_f){this.removeStyleSheet(id);var ss=_2.createElement("link");ss.setAttribute("rel","stylesheet");ss.setAttribute("type","text/css");ss.setAttribute("id",id);ss.setAttribute("href",_f);_2.getElementsByTagName("head")[0].appendChild(ss);},refreshCache:function(){return this.getRules(true);},cacheStyleSheet:function(ss){if(!_1){_1={};}try{var _12=ss.cssRules||ss.rules;for(var j=_12.length-1;j>=0;--j){_1[_12[j].selectorText]=_12[j];}}catch(e){}},getRules:function(_14){if(_1==null||_14){_1={};var ds=_2.styleSheets;for(var i=0,len=ds.length;i<len;i++){try{this.cacheStyleSheet(ds[i]);}catch(e){}}}return _1;},getRule:function(_18,_19){var rs=this.getRules(_19);if(!(_18 instanceof Array)){return rs[_18];}for(var i=0;i<_18.length;i++){if(rs[_18[i]]){return rs[_18[i]];}}return null;},updateRule:function(_1c,_1d,_1e){if(!(_1c instanceof Array)){var _1f=this.getRule(_1c);if(_1f){_1f.style[_3(_1d)]=_1e;return true;}}else{for(var i=0;i<_1c.length;i++){if(this.updateRule(_1c[i],_1d,_1e)){return true;}}}return false;},apply:function(el,_22){if(!(_22 instanceof Array)){var _23=this.getRule(_22);if(_23){var s=_23.style;for(var key in s){if(typeof s[key]!="function"){if(s[key]&&String(s[key]).indexOf(":")<0&&s[key]!="false"){try{el.style[key]=s[key];}catch(e){}}}}return true;}}else{for(var i=0;i<_22.length;i++){if(this.apply(el,_22[i])){return true;}}}return false;},applyFirst:function(el,id,_29){var _2a=["#"+id+" "+_29,_29];return this.apply(el,_2a);},revert:function(el,_2c){if(!(_2c instanceof Array)){var _2d=this.getRule(_2c);if(_2d){for(key in _2d.style){if(_2d.style[key]&&String(_2d.style[key]).indexOf(":")<0&&_2d.style[key]!="false"){try{el.style[key]="";}catch(e){}}}return true;}}else{for(var i=0;i<_2c.length;i++){if(this.revert(el,_2c[i])){return true;}}}return false;},revertFirst:function(el,id,_31){var _32=["#"+id+" "+_31,_31];return this.revert(el,_32);}};}();

Ext.util.ClickRepeater=function(el,_2){this.el=Ext.get(el);this.el.unselectable();Ext.apply(this,_2);this.events={"mousedown":true,"click":true,"mouseup":true};this.el.on("mousedown",this.handleMouseDown,this);if(this.preventDefault||this.stopDefault){this.el.on("click",function(e){if(this.preventDefault){e.preventDefault();}if(this.stopDefault){e.stopEvent();}},this);}if(this.handler){this.on("click",this.handler,this.scope||this);}};Ext.extend(Ext.util.ClickRepeater,Ext.util.Observable,{interval:20,delay:250,preventDefault:true,stopDefault:false,timer:0,docEl:Ext.get(document),handleMouseDown:function(){clearTimeout(this.timer);this.el.blur();if(this.pressClass){this.el.addClass(this.pressClass);}this.mousedownTime=new Date();this.docEl.on("mouseup",this.handleMouseUp,this);this.el.on("mouseout",this.handleMouseOut,this);this.fireEvent("mousedown",this);this.fireEvent("click",this);this.timer=this.click.defer(this.delay||this.interval,this);},click:function(){this.fireEvent("click",this);this.timer=this.click.defer(this.getInterval(),this);},getInterval:function(){if(!this.accelerate){return this.interval;}var _4=this.mousedownTime.getElapsed();if(_4<500){return 400;}else{if(_4<1700){return 320;}else{if(_4<2600){return 250;}else{if(_4<3500){return 180;}else{if(_4<4400){return 140;}else{if(_4<5300){return 80;}else{if(_4<6200){return 50;}else{return 10;}}}}}}}},handleMouseOut:function(){clearTimeout(this.timer);if(this.pressClass){this.el.removeClass(this.pressClass);}this.el.on("mouseover",this.handleMouseReturn,this);},handleMouseReturn:function(){this.el.removeListener("mouseover",this.handleMouseReturn);if(this.pressClass){this.el.addClass(this.pressClass);}this.click();},handleMouseUp:function(){clearTimeout(this.timer);this.el.removeListener("mouseover",this.handleMouseReturn);this.el.removeListener("mouseout",this.handleMouseOut);this.docEl.removeListener("mouseup",this.handleMouseUp);this.el.removeClass(this.pressClass);this.fireEvent("mouseup",this);}});

Ext.KeyNav=function(el,_2){this.el=Ext.get(el);Ext.apply(this,_2);if(!this.disabled){this.disabled=true;this.enable();}};Ext.KeyNav.prototype={disabled:false,defaultEventAction:"stopEvent",prepareEvent:function(e){var k=e.getKey();var h=this.keyToHandler[k];if(Ext.isSafari&&h&&k>=37&&k<=40){e.stopEvent();}},relay:function(e){var k=e.getKey();var h=this.keyToHandler[k];if(h&&this[h]){if(this.doRelay(e,this[h],h)!==true){e[this.defaultEventAction]();}}},doRelay:function(e,h,_b){return h.call(this.scope||this,e);},enter:false,left:false,right:false,up:false,down:false,tab:false,esc:false,pageUp:false,pageDown:false,del:false,home:false,end:false,keyToHandler:{37:"left",39:"right",38:"up",40:"down",33:"pageUp",34:"pageDown",46:"del",36:"home",35:"end",13:"enter",27:"esc",9:"tab"},enable:function(){if(this.disabled){if(Ext.isIE){this.el.on("keydown",this.relay,this);}else{this.el.on("keydown",this.prepareEvent,this);this.el.on("keypress",this.relay,this);}this.disabled=false;}},disable:function(){if(!this.disabled){if(Ext.isIE){this.el.un("keydown",this.relay);}else{this.el.un("keydown",this.prepareEvent);this.el.un("keypress",this.relay);}this.disabled=true;}}};

Ext.KeyMap=function(el,_2,_3){this.el=Ext.get(el);this.eventName=_3||"keydown";this.bindings=[];if(_2 instanceof Array){for(var i=0,_5=_2.length;i<_5;i++){this.addBinding(_2[i]);}}else{this.addBinding(_2);}this.keyDownDelegate=Ext.EventManager.wrap(this.handleKeyDown,this,true);this.enable();};Ext.KeyMap.prototype={stopEvent:false,addBinding:function(_6){var _7=_6.key,_8=_6.shift,_9=_6.ctrl,_a=_6.alt,fn=_6.fn,_c=_6.scope;if(typeof _7=="string"){var ks=[];var _e=_7.toUpperCase();for(var j=0,len=_e.length;j<len;j++){ks.push(_e.charCodeAt(j));}_7=ks;}var _11=_7 instanceof Array;var _12=function(e){if((!_8||e.shiftKey)&&(!_9||e.ctrlKey)&&(!_a||e.altKey)){var k=e.getKey();if(_11){for(var i=0,len=_7.length;i<len;i++){if(_7[i]==k){if(this.stopEvent){e.stopEvent();}fn.call(_c||window,k,e);return;}}}else{if(k==_7){if(this.stopEvent){e.stopEvent();}fn.call(_c||window,k,e);}}}};this.bindings.push(_12);},handleKeyDown:function(e){if(this.enabled){var b=this.bindings;for(var i=0,len=b.length;i<len;i++){b[i].call(this,e);}}},isEnabled:function(){return this.enabled;},enable:function(){if(!this.enabled){this.el.on(this.eventName,this.keyDownDelegate);this.enabled=true;}},disable:function(){if(this.enabled){this.el.removeListener(this.eventName,this.keyDownDelegate);this.enabled=false;}}};
