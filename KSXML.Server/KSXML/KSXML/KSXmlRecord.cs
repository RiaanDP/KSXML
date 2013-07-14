using System;
using System.Collections.Generic;
using System.Text;

namespace KSXML
{
    public class KSXmlRecord
    {
        private readonly Dictionary<String, Object> _dataMap = new Dictionary<String, Object>();

        public void SetFieldValue(String field, Object value)
        {
            if (_dataMap.ContainsKey(field))
            {
                _dataMap[field] = value;
            }
            else
            {
                _dataMap.Add(field, value);
            }
        }

        public string ToXml()
        {
            var sbuilder = new StringBuilder();

            sbuilder.Append("<ksxmlrecord>");
            foreach (var keyValuePair in _dataMap)
            {
                sbuilder.AppendFormat("<{0}>{1}</{0}>", keyValuePair.Key, keyValuePair.Value);
            }
            sbuilder.Append("</ksxmlrecord>");

            return sbuilder.ToString();
        }
    }
}
