using System;
using System.Web.Services;

namespace KSXML.Example
{
    [WebService(Namespace = "http://ksxml.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class PlayListService : System.Web.Services.WebService
    {
        [WebMethod]
        public string GetTrackInformation(int id)
        {
            try
            {
                return new KSXmlResponse
                    {
                        Code = 1,
                        Payload = DataLayer.Instance.GetTrackInformation(id).ToXml()
                    }.ToXml();
            }
            catch (Exception ex)
            {
                return new KSXmlResponse {Code = 0, Message = ex.Message}.ToXml();
            }
        }

        [WebMethod]
        public string GetAllTrackNames()
        {
            try
            {
                return new KSXmlResponse
                {
                    Code = 1,
                    Payload = DataLayer.Instance.GetAllTrackNames().ToXml()
                }.ToXml();
            }
            catch (Exception ex)
            {
                return new KSXmlResponse { Code = 0, Message = ex.Message }.ToXml();
            }
        }
    }
}