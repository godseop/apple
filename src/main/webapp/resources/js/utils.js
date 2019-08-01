class Utils {

    static formatLocalDateTime(localDateTime) {
        // localDateTime likely '2019-06-22T06:35:59'
        if (localDateTime == null) return null;
        return moment(localDateTime).format("YYYY-MM-DD HH:mm");
    }

    static getDateStamp(offset) {
        offset = Number.isInteger(offset) ? offset : 0;
        return moment().add(offset, 'days').format("YYYY-MM-DD");
    }

    static getDateTimeStamp(offset) {
        offset = Number.isInteger(offset) ? offset : 0;
        return moment().add(offset, 'days').format("YYYY-MM-DD HH:mm");
    }

    static getDateTimeStampByMillis(milliseconds) {
        if (!Number.isInteger(milliseconds)) return null;
        return moment(milliseconds).format("YYYY-MM-DD HH:mm");
    }

}