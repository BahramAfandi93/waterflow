package az.pashabank.ips.integration.logger

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.util.HtmlUtils
import java.util.Arrays
import java.util.EnumSet

class DPLogger private constructor(private val logger: Logger) {

    companion object {
        fun getLogger(clazz: Class<*>): DPLogger {
            return DPLogger(LoggerFactory.getLogger(clazz))
        }
    }

    private fun filterValues(vararg argArray: Any?): Array<Any> {
        return Arrays.stream(argArray)
            .map { a -> a?.let { filterValue(a) } }.toArray()
    }

    private fun filterValue(a: Any): Any {
        return a as? Throwable ?: HtmlUtils.htmlEscape(
            EnumSet.allOf(FilterPattern::class.java)
                .stream()
                .reduce(a,
                    { o, f -> o.toString().replace(f.regexp.toRegex(), f.mask) },
                    { s1, s2 -> s1.toString() + s2.toString() })
                .toString()
        )
            .replace("\r", "&cr;")
            .replace("\n", "&lf;")
    }

    fun get(): String = logger.name

    fun trace(s: String, vararg args: Any?) {
        if (logger.isTraceEnabled) {
            logger.trace(s, *filterValues(*args))
        }
    }

    fun debug(s: String, vararg args: Any?) {
        if (logger.isDebugEnabled) {
            logger.debug(s, *filterValues(*args))
        }
    }

    fun info(s: String, vararg args: Any?) {
        if (logger.isInfoEnabled) {
            logger.info(s, *filterValues(*args))
        }
    }

    fun warn(s: String, vararg args: Any?) {
        if (logger.isWarnEnabled) {
            logger.warn(s, *filterValues(*args))
        }
    }

    fun error(s: String, vararg args: Any?) {
        if (logger.isErrorEnabled) {
            logger.error(s, *filterValues(*args))
        }
    }

    private enum class FilterPattern(val regexp: String, val mask: String) {
        NAME_SURNAME("[A-Z]+[ ]+[A-Z]+[ ]?+[A-Z]?", "**** *******"),
        PIN("\\b([A-Z]+\\d[A-Z\\d]+)|(\\d+[A-Z][A-Z\\S]+)\\b", "*******"),
        EMAIL("\\b([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})\\b", "*@*.*"),
        MOBILE_NUMBER("\\b(\\+?\\d{1,3}[- ]?)?\\d{9,10}\\b", "**********"),
        CARD_PAN_OR_ID("\\b\\d{16}\\b", "****************");
    }
}