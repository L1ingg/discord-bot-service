# Discord Bot Service

Description
- Discord bot that provides a slash command to confirm account linking. The bot sends a Kafka event to notify the account-linking service.

Environment variables (see `docker-compose.yml`)
- `SPRING_KAFKA_BOOTSTRAP_SERVERS` - Kafka bootstrap address
- `SPRING_KAFKA_CONSUMER_GROUP_ID` - Kafka consumer group id
- `BOT_TOKEN` - Discord bot token (required)

Ports
- Exposed in docker-compose as `9094:8080` (host:container).

Usage / Command

- Slash command: `/link`
	- Option: `code` (string) — the 6-digit code provided by the account-linking request.
	- Example usage in Discord: `/link code:123456`
	- Behavior: when a user runs `/link code:<code>` the bot sends a Kafka event to topic `account.linking` with the JSON payload:

```json
{
	"id": "<discord-user-id>",
	"username": "<discord-username>",
	"code": "<code>",
	"provider": "discord"
}
```

	- The bot replies to the user with an ephemeral message `In process...`.

