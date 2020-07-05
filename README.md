# Elapsed DateTime calculator
 
[![Maven Central](https://img.shields.io/maven-central/v/com.github.marlonlom/elapsed_time.svg)](http://www.mvnrepository.com/artifact/com.github.marlonlom/elapsed_time)
![Bintray](https://img.shields.io/bintray/v/marlonlom/ElapsedTimes/elapsed_times)
[![Build Status](https://travis-ci.com/marlonlom/elapsed_times.svg?branch=master)](https://travis-ci.com/marlonlom/elapsed_times)


Utility library for, given a start and a end dates, obtain the elapsed time between the two dates in terms of years, months and days.

## Examples:
- 1 year, 1 month, 12 days
- 7 days
- 3 month, 19 days
- 1 year, 24 days


## Usage:

### Import as a dependency:

Gradle:

```
compile 'com.github.marlonlom:elapsed_time:$latestVersion'
```

Maven:

```xml
<dependency>
  <groupId>com.github.marlonlom</groupId>
  <artifactId>elapsed_time</artifactId>
  <version>$latestVersion</version>
</dependency>
```

### Use it in your code:

Use it passing two dates, a starting one and the ending one, for obtaining the duration elapsed between those, that contains info about days, months and years (in reverse order, lol). 

Classes to keep in mind:

1. **TimeCount:** Describes the elapsed time count (in terms of years,months,days) between two dates.
2. **ElapsedTimes:** The Utility class.

```kotlin
/* 
 * Pass two dates, you can do so using java.util.Calendar or another class that 
 * handles java.util.Date objects, its required to use Date objects. 
 * in the snippet, its used the java.text.SimpleDateFormat for generate 
 * two dates using strings in the date format provided. 
 */

val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
val startDate = sdf.parse("23/09/2015")
val endDate = sdf.parse("04/11/2016")

/* Given two dates, obtain the elapsed time between those dates. */
val elapsedTime = ElapsedTimes.from(startDate).to(endDate).compare()

println("$elapsedTime") // prints '1 year,1 month, 11 days'
```

### Using actual date as the end date:
You can pass the starting date as normal, when done, use the method _'toNow()'_ for comparing using the actual datetime as the end date.

```kotlin
val twoDaysBeforeNow = Calendar.getInstance().apply { add(Calendar.DATE, -2) }
val elapsedTime = ElapsedTimes.from(twoDaysBeforeNow.time).toNow().compare()
println("$elapsedTime") // prints '2 days'
```

#### Using a starting date that is after the end date:
When comparing a starting date that is after the ending date, the utility throws an java.lang.IllegalArgumentException.


#### Using same date as start + end date:
When starting date is equal to the ending date, the utility returns zero years, zero months and zero days.



## Spread the word

If you like this library, please tell others about it :thumbsup::thumbsup:

<a href="https://twitter.com/intent/tweet?text=Trying%20to%20show%20relative%20date%20time%20texts%3F%20Check%20out%20this%20awesome%20library%20on%20Github%3A%20https://github.com/marlonlom/elapsed_time" target="_blank" title="share to twitter" style="width:100%"><img src="https://github.com/marlonlom/staticmaps_builder/blob/master/design/twitter_icon.png" title="Share on Twitter" width="35" height=35 />
<a href="https://plus.google.com/share?url=https://github.com/marlonlom/elapsed_time" target="_blank" title="share to G+" style="width:100%"><img src="https://github.com/marlonlom/staticmaps_builder/blob/master/design/googleplus_icon.png" target="_blank"  title="Share on Google+" width="35" height=35 />
<a href="https://www.facebook.com/sharer/sharer.php?u=https://github.com/marlonlom/elapsed_time" target="_blank" title="share to facebook" style="width:100%"><img src="https://github.com/marlonlom/staticmaps_builder/blob/master/design/facebook_icon.png" title="Share on Facebook" width="35" height=35 /></a>

 - Follow me on **Twitter**: [**@Marlonlom**](https://twitter.com/marlonlom)
 - Contact me on **LinkedIn**: [**Marlonlom**](https://co.linkedin.com/in/marlonlom)


### License

```
Copyright 2020 marlonlom

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
