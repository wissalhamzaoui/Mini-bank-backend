package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.apache.commons.lang3.StringUtils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

@SuppressWarnings("deprecation")
@Configuration
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Autowired
	private MongoProperties mongoProperties;

	@Override
	public MongoClient mongoClient() {

		// SERVER INFO
		ServerAddress serverAddress = new ServerAddress(mongoProperties.getHost(), mongoProperties.getPort());

		// REMOTE SERVER
		if (StringUtils.isNotBlank(mongoProperties.getUsername())) {
			// CREDENTIAL
			MongoCredential credential = MongoCredential.createCredential(mongoProperties.getUsername(),
					mongoProperties.getAuthenticationDatabase(), mongoProperties.getPassword());

			// CREATE MONGO CLIENT
			return new MongoClient(serverAddress, credential, MongoClientOptions.builder().build());
		}

		// LOCAL HOST
		return new MongoClient(serverAddress);

	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getDatabase();
	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

}