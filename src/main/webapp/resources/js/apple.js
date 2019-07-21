var Apple = (function() {

    let COMMON = {
        token: null,
    };

    const TALK = {
        message: null,
    };

    return {
        init: (param) => {
            COMMON.token = param.token;
        },

        talk: (param) => {
            let talkMessage = {};
            $.extend(true, talkMessage, TALK, COMMON);
            talkMessage.message = param.message;

            return Object.values(talkMessage);
        },


    }
}());