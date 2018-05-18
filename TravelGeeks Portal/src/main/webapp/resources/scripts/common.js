AWS.config.accessKeyId="XXXX";
AWS.config.secretAccessKey="XXXX";
AWS.config.region="XXXX";

var polly=new AWS.Polly();

var params={
	OutputFormat:"mp3",
	Text:"Welcome to Travel Geeks, happy blogging!!",
	TextType:"text",
	VoiceId:"Joanna"

};

polly.synthesizeSpeech(params, function(err, data){

	if(err)
	{
		console.log(err,err.stack);
	}
	else{
		var uInt8Array=new Uint8Array(data.AudioStream);
		var arraybuffer=uInt8Array.buffer;
		var blob=new Blob([arraybuffer]);

		var audio=$('audio');
		var url=URL.createObjectURL(blob);
		audio[0].src=url;
		audio[0].play();
	}
});