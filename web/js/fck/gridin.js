FCKConfig.AutoDetectPasteFromWord = false ;
FCKConfig.FlashBrowser = false ;
FCKConfig.ImageBrowser = false ;
FCKConfig.LinkUpload = false ;
FCKConfig.ImageUpload = true;
FCKConfig.ImageUploadURL = '/preppa/test/ad.jpg'
FCKConfig.ImageUploadAllowedExtensions = ".(jpg|gif|jpeg|png|bmp)$" ;
FCKConfig.PluginsPath = '/preppa/js/fck/plugins/' ;
FCKConfig.SkinPath =  '/preppa/js/fck/skins/Office2007Real/';
FCKConfig.ToolbarSets["Gridin"] = [
['Source','DocProps','-','Save','Preview','-','Templates'],
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
