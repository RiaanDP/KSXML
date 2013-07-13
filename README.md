KSXML
=====

Easily transmit and parse XML data between Java powered clients and .NET based Web Services.

I created this .NET library and Java code files as part of an Android application I am working on. The project required me to load and save data using a C# Web Service. Rather than implementing a unique data parsing algorithm for every web service call, I opted for a standardized approach throughout my application. Although I used KSOAP2 during my implementation (hence the naming origin), the client KSXML classes should work to parse KSXML reponse data irrelevant of which transfer mechanism was used.

The KSXML .NET library (in KSXML.Server) contains 3 classes:

KSXmlRecord - Represents a single record as key-value pair data.
KSXmlRecordSet - Represents a list of KSXmlRecord objects.
KSXmlResponse - Represents a response which contains payload and response code data. The payload can be any serialized data.

Simply reference the library as-is and use as indicated in the accompanying example Web Service code.

And on the Java/Android side (in KSXML.Client):

KSXmlRecord - Represents a single record as key-value pair data.
KSXmlRecordSet - Represents a list of KSXmlRecord objects.
KSXmlRecordResponse - Represents a response containing a single record.
KSXmlRecordSetReponse - Represents a response containing a set of records.

These classes can be found in the package com/ksxml/source/ and can be copied to an android project to parse response data generated with the KSXML library.

This solution was developed to make implementation and adoption as easy and straight forward as possible. The classes currently do not check for data that may cause malformed XML - neither does it include any compression or security optimizations.


THE SOFTWARE IS PROVIDED 'AS IS', WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
