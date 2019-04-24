import groovy.json.JsonSlurper
import org.sonatype.nexus.repository.config.Configuration

parsed_args = new JsonSlurper().parseText(args)

configuration = new Configuration(
  repositoryName: parsed_args.name,
  recipeName: 'docker-hosted',
  online: true,
  attributes: [
    docker: [
      httpPort: 8081,
      v1Enabled : true
    ],
    storage: [
      //writePolicy: parsed_args.write_policy.toUpperCase(),
      blobStoreName: 'default',
      strictContentTypeValidation: true
    ]
  ]
)

def existingRepository = repository.getRepositoryManager().get(parsed_args.name)

if (existingRepository != null) {
    existingRepository.stop()
    configuration.attributes['storage']['blobStoreName'] = existingRepository.configuration.attributes['storage']['blobStoreName']
    existingRepository.update(configuration)
    existingRepository.start()
} else {
    repository.getRepositoryManager().create(configuration)
}