using System;
using System.Text;

namespace KSXML
{
    public class KSXmlResponse
    {
        public int Code { get; set; }
        public String Message { get; set; }
        public String Payload { get; set; }

        public string ToXml()
        {
            var sbuilder = new StringBuilder();

            sbuilder.Append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            sbuilder.Append("<ksxmlresponse>");
            sbuilder.Append(String.Format("<code>{0}</code>", Code));
            sbuilder.Append(String.Format("<message>{0}</message>", Message ?? ""));
            sbuilder.Append(String.Format("<payload>{0}</payload>", Payload ?? ""));
            sbuilder.Append("</ksxmlresponse>");

            return sbuilder.ToString();
        }
    }
}
