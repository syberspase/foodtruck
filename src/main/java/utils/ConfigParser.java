package utils;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import com.socrata.builders.SoqlQueryBuilder;
import com.socrata.model.soql.OrderByClause;
import com.socrata.model.soql.SoqlQuery;
import com.socrata.model.soql.SortOrder;

import exceptions.FinderException;

/**
 * Configuration properties file parser
 */
public class ConfigParser {
    // Default config file path
    private final String defaultConfigPath = "/config.properties";

    // Properties
    private Properties props = new Properties();
    private int queryLimit = 5;
    private int queryOffset = 0;

    /**
     * Constructor with configuration properties file path.
     * 
     * @param confPath configuration properties file path, if null, use system default one.
     * @throws FinderException
     */
    public ConfigParser(String confPath) throws FinderException {
        if (confPath == null)
            confPath = defaultConfigPath;
        try {
            props.load(ConfigParser.class.getResourceAsStream(confPath));
            queryLimit = Integer.valueOf(props.getProperty(Config.QUERY_LIMIT));
            queryOffset = Integer.valueOf(props.getProperty(Config.QUERY_OFFSET));
        } catch (IOException e) {
            throw new FinderException(e.getMessage(), e);
        }
    }

    /**
     * Returns service URL
     * 
     * @return service URL
     */
    public String getServiceUrl() {
        return props.getProperty(Config.SERVICE_URL);
    }

    /**
     * Returns resource ID
     * 
     * @return resource ID
     */
    public String getResourceId() {
        return props.getProperty(Config.SERVIE_RESOURCE_ID);
    }

    /**
     * Builds SoQL query object
     * 
     * @param whereClauseValues Values to replace place holders defined in configuration properties.
     * @return SoQL query object
     */
    public SoqlQuery buildQuery(String... whereClauseValues) {
        SoqlQueryBuilder builder = new SoqlQueryBuilder();

        // Sets select fields
        String[] fields = props.getProperty(Config.QUERY_SELECT_FIELDS).split(",");
        for (String field : fields)
            builder.addSelectPhrase(field);

        // Sets where clause
        String whereClause = props.getProperty(Config.QUERY_WHERE_CLAUSE);
        if (whereClause != null && whereClause.trim().length() > 0)
            builder.setWhereClause(MessageFormat.format(whereClause, whereClauseValues));

        // Sets limit
        builder.setLimit(queryLimit);

        // Sets offset
        builder.setOffset(queryOffset);

        // Sets offset
        builder.addOrderByPhrase(
                new OrderByClause(Boolean.parseBoolean(props.getProperty(Config.QUERY_ORDER_ASC)) ? SortOrder.Ascending
                        : SortOrder.Descending, props.getProperty(Config.QUERY_ORDER_BY)));

        queryOffset++;
        return builder.build();
    }

    // Key mapping to key defined in configuration properties
    private static class Config {
        public static final String SERVICE_URL = "service_url";
        public static final String SERVIE_RESOURCE_ID = "servie_resource_id";
        public static final String QUERY_SELECT_FIELDS = "query_select_fields";
        public static final String QUERY_WHERE_CLAUSE = "query_where_clause";
        public static final String QUERY_LIMIT = "query_limit";
        public static final String QUERY_OFFSET = "query_offset";
        public static final String QUERY_ORDER_BY = "query_order_by";
        public static final String QUERY_ORDER_ASC = "query_order_asc";
    }
}
