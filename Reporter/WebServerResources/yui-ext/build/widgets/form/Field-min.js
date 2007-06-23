/*
 * Ext - JS Library 1.0 Alpha 3 - Rev 4
 * Copyright(c) 2006-2007, Jack Slocum.
 * 
 * http://www.extjs.com/license.txt
 */

Ext.form.Field=function(_1){Ext.form.Field.superclass.constructor.call(this,_1);this.addEvents({focus:true,blur:true,specialkey:true,change:true,invalid:true,valid:true});};Ext.extend(Ext.form.Field,Ext.Component,{invalidClass:"x-form-invalid",invalidText:"The value in this field is invalid",focusClass:"x-form-focus",validationEvent:"keyup",validationDelay:250,defaultAutoCreate:{tag:"input",type:"text",size:"20",autocomplete:"off"},fieldClass:"x-form-field",hasFocus:false,msgTarget:"qtip",msgFx:"normal",applyTo:function(_2){this.target=_2;this.el=Ext.get(_2);this.render(this.el.dom.parentNode);return this;},onRender:function(ct){if(this.el){this.el=Ext.get(this.el);if(!this.target){ct.dom.appendChild(this.el.dom);}}else{var _4=typeof this.autoCreate=="object"?this.autoCreate:this.defaultAutoCreate;if(this.id&!_4.id){_4.id=this.id;}if(!_4.name){_4.name=this.name||this.id;}this.el=ct.createChild(_4);}var _5=this.el.dom.type;if(_5){if(_5=="password"){_5="text";}this.el.addClass("x-form-"+_5);}if(!this.customSize&&(this.width||this.height)){this.setSize(this.width||"",this.height||"");}if(this.style){this.el.applyStyles(this.style);delete this.style;}if(this.readOnly){this.el.dom.readOnly=true;}this.el.addClass([this.fieldClass,this.cls]);this.initValue();},initValue:function(){if(this.value!==undefined){this.setValue(this.value);}else{if(this.el.dom.value.length>0){this.setValue(this.el.dom.value);}}},afterRender:function(){this.initEvents();},fireKey:function(e){if(e.isNavKeyPress()){this.fireEvent("specialkey",this,e);}},initEvents:function(){this.el.on(Ext.isIE?"keydown":"keypress",this.fireKey,this);this.el.on("focus",this.onFocus,this);this.el.on("blur",this.onBlur,this);},onFocus:function(){if(!Ext.isOpera){this.el.addClass(this.focusClass);}this.hasFocus=true;this.startValue=this.getValue();this.fireEvent("focus",this);},onBlur:function(){this.el.removeClass(this.focusClass);this.hasFocus=false;if(this.validationEvent!="blur"){this.validate();}var v=this.getValue();if(v!=this.startValue){this.fireEvent("change",this,v,this.startValue);}this.fireEvent("blur",this);},setSize:function(w,h){if(!this.rendered){this.width=w;this.height=h;return;}if(w){this.el.setWidth(w);}if(h){this.el.setHeight(h);}var h=this.el.dom.offsetHeight;},isValid:function(){return this.validateValue(this.getValue());},validate:function(){if(this.validateValue(this.getRawValue())){this.clearInvalid();}},validateValue:function(_a){return true;},markInvalid:function(_b){if(!this.rendered){return;}this.el.addClass(this.invalidClass);_b=_b||this.invalidText;switch(this.msgTarget){case "qtip":this.el.dom.qtip=_b;break;case "title":this.el.dom.title=_b;break;case "under":if(!this.errorEl){var _c=this.el.findParent(".x-form-element",5,true);this.errorEl=_c.createChild({cls:"x-form-invalid-msg"});this.errorEl.setWidth(_c.getWidth()-20);}this.errorEl.update(_b);Ext.form.Field.msgFx[this.msgFx].show(this.errorEl,this);break;default:var t=Ext.getDom(this.msgTarget);t.innerHTML=_b;t.style.display=this.msgDisplay;break;}this.fireEvent("invalid",this,_b);},clearInvalid:function(){if(!this.rendered){return;}this.el.removeClass(this.invalidClass);switch(this.msgTarget){case "qtip":this.el.dom.qtip="";break;case "title":this.el.dom.title="";break;case "under":if(this.errorEl){var p=this.el.findParent(".x-form-item",5,true);if(p){p.removeClass("x-form-item-msg");Ext.form.Field.msgFx[this.msgFx].hide(this.errorEl,this);}}break;default:var t=Ext.getDom(this.msgTarget);t.innerHTML="";t.style.display="none";break;}this.fireEvent("valid",this);},getRawValue:function(){return this.el.getValue();},getValue:function(){return this.el.getValue();},setRawValue:function(v){return this.el.dom.value=v;},setValue:function(v){this.value=v;if(this.rendered){this.el.dom.value=v;this.validate();}}});Ext.form.Field.msgFx={normal:{show:function(_12,f){_12.setDisplayed("block");},hide:function(_14,f){_14.setDisplayed(false).update("");}},slide:{show:function(_16,f){_16.slideIn("t",{stopFx:true});},hide:function(_18,f){_18.slideOut("t",{stopFx:true,useDisplay:true});}},slideRight:{show:function(_1a,f){_1a.fixDisplay();_1a.alignTo(f.el,"tl-tr");_1a.slideIn("l",{stopFx:true});},hide:function(_1c,f){_1c.slideOut("l",{stopFx:true,useDisplay:true});}}};