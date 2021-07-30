# HTTPCLIENTKG

HTTP is the way modern applications network. It’s how we exchange data & media. 
Doing HTTP efficiently makes your stuff load faster and saves bandwidth.

## Usage

Add it in your root build.gradle at the end of repositories:

```

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

```

Step 2. Add the dependency

```

	dependencies {
	        implementation 'com.github.KutuGondrong:httpclientkg:0.0.1'
	}

```
## How To Use

```
val httpClient = HttpClientKG.dslDefaultHttpClient {
                     baseUrl = BuildConfig.SERVER_BASE_URL
                     properties {
                         property {
                             key = "Content-Type"
                             value = "application/json; utf-8"
                         }
                         property {
                             key = "Accept"
                             value = "application/json"
                         }
                         property {
                             key = "Authorization"
                             value = "Client-ID ${BuildConfig.API_KEY}"
                         }
                     }
                 }

httClient.execute(setting : SettingRequestKG)
```

## Website
[kutugondrong.com](https://kutugondrong.com/)
