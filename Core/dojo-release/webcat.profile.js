dependencies = {
    layers: [
        {
            name: 'BasePage.js',
            dependencies: [
                "dijit.Dialog",
                "dijit.Menu",
                "dijit.TitlePane",
                "dijit.Toolbar",
                "dijit.Tooltip",
                "dijit.form.Button",
                "dijit.form.CheckBox",
                "dijit.form.ComboBox",
                "dijit.form.DateTextBox",
                "dijit.form.FilteringSelect",
                "dijit.form.Form",
                "dijit.form.NumberSpinner",
                "dijit.form.Slider",
                "dijit.form.TimeTextBox",
                "dijit.form.ValidationTextBox",
                "webcat.global",
                "webcat.ContentPane",
                "webcat.ObjectTable",
                "webcat.Tree"
            ]
        }
    ],
    prefixes: [
        [ "dijit", "../dijit" ],
        [ "dojox", "../dojox" ],
        [ "webcat", "../webcat" ]
    ]
};