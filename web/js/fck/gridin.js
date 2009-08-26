FCKConfig.AutoDetectPasteFromWord = false ;
FCKConfig.PluginsPath = '/preppa/js/fck/plugins/' ;
FCKConfig.SkinPath =  '/preppa/js/fck/skins/office2007/';
FCKConfig.ToolbarSets["Gridin"] = [
['Source','DocProps','-','Save','NewPage','Preview','-','Templates'],
['Cut','Copy','Paste','PasteText','PasteWord','-','Print','SpellCheck'],
['Undo','Redo','-','Find','Replace','-','SelectAll','RemoveFormat'],
'/',
['Bold','Italic','Underline','StrikeThrough','-','Subscript','Superscript'],
['OrderedList','UnorderedList','-','Outdent','Indent','Blockquote'],
['JustifyLeft','JustifyCenter','JustifyRight','JustifyFull'],
['Link','Unlink','Anchor'],
'/',
['Style','FontFormat','FontName','FontSize'],
['TextColor'],['Equation']
] ;
FCKConfig.Plugins.Add( 'equation' , 'en');
