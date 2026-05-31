# Discord Bot Service

Discord bot for ConnectMe account linking.

Provides a `/link` slash command that forwards linking requests to Kafka.



### Bot

* `BOT_TOKEN` - Discord bot token (required)

## Port

| Host | Container |
| ---- | --------- |
| 9094 | 8080      |

## Command

### `/link`

Usage:

```
/link code:<6-digit-code>
```

Behavior:

* Sends event to Kafka topic `account.linking`
* Payload:

```json id="k8p0zn"
{
  "id": "<discord-user-id>",
  "username": "<discord-username>",
  "code": "<code>",
  "provider": "discord"
}
```

* Bot response: ephemeral message `In process...`


